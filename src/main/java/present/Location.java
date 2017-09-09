package present;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A geographic location
 */
public class Location {

  private final double latitude;
  private final double longitude;

  private static final int ONE_RADIAN_IN_KM = 6371;

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

  static AtomicInteger distanceComputations = new AtomicInteger();
}
