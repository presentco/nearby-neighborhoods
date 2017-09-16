package present;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A geographic location
 */
public class Location {

  private static final int EARTH_RADIUS_KM = 6372;
  private final double latitude;
  private final double longitude;

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

    double latitude1 = Math.toRadians(latitude);
    double longitude1 = Math.toRadians(longitude);
    double latitude2 = Math.toRadians(other.latitude);
    double longitude2 = Math.toRadians(other.longitude);

    double angleInRadians = Math.acos(
            Math.sin(latitude1) * Math.sin(latitude2)
                    + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude1 - longitude2));
    return EARTH_RADIUS_KM * angleInRadians;
  }

  static AtomicInteger distanceComputations = new AtomicInteger();
}
