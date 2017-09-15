package present;

import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Math;

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
    // throw new UnsupportedOperationException("Please implement!");

    // Reference: http://www.movable-type.co.uk/scripts/latlong.html
    // Reference: http://www.movable-type.co.uk/scripts/gis-faq-5.1.html

    // R is earth's radius in km
    double R = 6371.0;

    // convert degrees to radians
    double lat1 = Math.toRadians(this.latitude);
    double lat2 = Math.toRadians(other.latitude);
    double lon1 = Math.toRadians(this.longitude);
    double lon2 = Math.toRadians(other.longitude);

    double deltaLon = lon2 - lon1;
    double deltaLat = lat2 - lat1;
    double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLon/2) * Math.sin(deltaLon/2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    double d = R * c;

    return d;
  }

  static AtomicInteger distanceComputations = new AtomicInteger();
}
