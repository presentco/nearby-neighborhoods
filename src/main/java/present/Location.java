package present;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A geographic location
 */
public class Location {

    /**
     * Earth radius in kilometers
     */
    public static final double R = 6372;

    private final double latitude;
    private final double longitude;

    /**
     * Constructs a new set of coordinates
     *
     * @param latitude  in degrees
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
        return calculateDistance(Math.toRadians(latitude()), Math.toRadians(longitude()),
                Math.toRadians(other.latitude()), Math.toRadians(other.longitude()));
    }

    /**
     * Calculates the distance between two points using the Haversine formula.
     *
     * @param latitude1  The latitude of point 1 in radians.
     * @param longitude1 The longitude of point 1 in radians.
     * @param latitude2  The latitude of point 2 in radians.
     * @param longitude2 The longitude of point 2 in radians.
     * @return The distance between two points in kilometers.
     */
    private double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double a = Math.pow(Math.sin((latitude2 - latitude1) / 2), 2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin((longitude2 - longitude1) / 2), 2);
        return R * 2 * Math.asin(Math.min(1, Math.sqrt(a)));
    }

    static AtomicInteger distanceComputations = new AtomicInteger();
}