package present;

public class DistanceNeighborhood implements Comparable<DistanceNeighborhood> {

  double distance;
  Neighborhood neighborhood;

  public DistanceNeighborhood(double distance, Neighborhood neighborhood) {
    this.distance = distance;
    this.neighborhood = neighborhood;
  }

  public double getDistance() {
    return distance;
  }

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  @Override
  public int compareTo(DistanceNeighborhood o) {

    if (this.getDistance() < o.getDistance()) {
      return -1;
    } else if (this.getDistance() > o.getDistance()) {
      return 1;
    } else {
      return 0;
    }
  }

}
