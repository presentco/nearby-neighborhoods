package present;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A geographic location
 */
public class Location {

  private final double latitude;
  private final double longitude;
  private static final int ONE_RADIAN_IN_KM = 6371;

  //Recommendation would be to use LRU cache with a max number of objects specified to avoid OOM issues
  private static final Map<DistanceCacheKey, Double> DISTANCE_CACHE = new HashMap<>();

  static AtomicInteger distanceComputations = new AtomicInteger();

  /**
   * Constructs a new set of coordinates
   *
   * @param latitude in degrees
   * @param longitude in degrees
   */
  public Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /** Returns the latitude in degrees. */
  public double latitude() {
    return latitude;
  }

  /** Returns the latitude in degrees. */
  public double longitude() {
    return longitude;
  }

  /**
   * Computes the great-circle distance (https://en.wikipedia.org/wiki/Great-circle_distance)
   * between this location and another in km.
   */
  public double distanceTo(Location other) {
    distanceComputations.incrementAndGet();

    return getDistanceFromCache(other);
  }

  /**
   * Reads distance from cache. If found, returns it or else computes the distance, puts it in cache and then return it.
   *
   * @param other location to get the distance to
   * @return distance to other location
   */
  private Double getDistanceFromCache(Location other) {
    DistanceCacheKey distanceCacheKey = new DistanceCacheKey(this, other);
    Double distance = DISTANCE_CACHE.get(distanceCacheKey);

    if (distance != null) {
      return distance;
    }

    distance = getDistance(other);

    DISTANCE_CACHE.put(distanceCacheKey, distance);

    return distance;
  }

  /**
   * getDistance computes the distance from current location using the cos formula.
   *
   * @param other location to compute the distance to
   * @return distance to other location
   */
  private Double getDistance (Location other) {
    double currentLatitude = Math.toRadians(latitude);
    double currentLongitude = Math.toRadians(longitude);
    double otherLatitude = Math.toRadians(other.latitude);
    double otherLongitude = Math.toRadians(other.longitude);

    // Using the cos formula
    double angleInRadians = Math.acos(
            Math.sin(currentLatitude) * Math.sin(otherLatitude) +
                    Math.cos(currentLatitude) * Math.cos(otherLatitude) * Math.cos(currentLongitude - otherLongitude)
    );

    return angleInRadians * ONE_RADIAN_IN_KM;
  }

  /**
   * Private inner class for the cache key for DISTANCE_CACHE
   *
   * The motivation of this class is because distance between Point 1 to 2 is the same as Point 2 to 1. So this overrides equals to handle that use-case
   */
  private class DistanceCacheKey {
    Location current;
    Location other;

    /**
     * Construct a new DistanceCacheKey
     *
     * @param current location
     * @param other location
     */
    public DistanceCacheKey(Location current, Location other) {
      this.current = current;
      this.other = other;
    }

    @Override
    public boolean equals(Object object) {
      if (object == null) {
        return false;
      }

      if (object == this) {
        return true;
      }

      if (!(object instanceof DistanceCacheKey)) {
        return false;
      }

      DistanceCacheKey distanceCacheKey = (DistanceCacheKey) object;

      return (current.equals(distanceCacheKey.current) && other.equals(distanceCacheKey.other)) ||
              (current.equals(distanceCacheKey.other) && other.equals(distanceCacheKey.current));
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result
              + ((current == null) ? 0 : current.hashCode())
              + ((other == null) ? 0 : other.hashCode());

      return result;
    }
  }
}
