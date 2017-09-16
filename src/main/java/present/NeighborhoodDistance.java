package present;

/**
 * Just a wrapper to keep a neighborhood packaged with the distance from a location so they can be sorted.
 */
public class NeighborhoodDistance {

  public NeighborhoodDistance(double distance, Neighborhood neighborhood) {
    this.distance = distance;
    this.neighborhood = neighborhood;
  }

  private double distance;
  private Neighborhood neighborhood;

  public Neighborhood neighborhood() {
    return neighborhood;
  }

  public double distance() {
    return distance;
  }
}
