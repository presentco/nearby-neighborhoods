package present;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A geographic location
 */
public class Location {

  private final double latitude;
  private final double longitude;
  private static final int EARTH_RADIUS = 6371;

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

  /**
   * Returns the latitude in degrees.
   */
  public double latitude() {
    return latitude;
  }

  /**
   * Returns the latitude in degrees.
   */
  public double longitude() {
    return longitude;
  }

  /**
   * Computes the great-circle distance (https://en.wikipedia.org/wiki/Great-circle_distance)
   * between this location and another in km.
   */
  public double distanceTo(Location other) {
    distanceComputations.incrementAndGet();

    double latOrigin =  Math.toRadians(this.latitude());
    double longOrigin =  Math.toRadians(this.longitude());
    double latDestination =  Math.toRadians(other.latitude());
    double longDestination =  Math.toRadians(other.longitude());

    double deltaLat = latDestination - latOrigin;
    double deltaLong = longDestination - longOrigin;

    double a = Math.pow(Math.sin(deltaLat / 2), 2) + Math.pow(Math.sin(deltaLong / 2), 2) * Math.cos(latOrigin) * Math.cos(latDestination);
    double c = 2 * Math.asin(Math.sqrt(a));

    return EARTH_RADIUS * c;

  }

  static AtomicInteger distanceComputations = new AtomicInteger();
}
