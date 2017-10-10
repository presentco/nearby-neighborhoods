package present;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A geographic location
 */
public class Location {

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

    throw new UnsupportedOperationException("Please implement!");
  }

  static AtomicInteger distanceComputations = new AtomicInteger();
}
