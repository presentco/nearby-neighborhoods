package present;

import com.google.common.collect.MinMaxPriorityQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * Searches {@link Neighborhoods#ALL}.
 */
public class Search {

  /**
   * Finds the {@code n} neighborhoods nearest to the given location.
   *
   * @param location to search near
   * @param n number of neighborhoods to return
   * @return the {@code n} nearest neighborhoods, ordered nearest to farthest
   */
  public static List<Neighborhood> near(Location location, int n) {

    MinMaxPriorityQueue<DistanceNeighborhood> nearestNeighborhoods = MinMaxPriorityQueue
        .maximumSize(n).create();

    for (Neighborhood neighborhood : Neighborhoods.ALL) {
      addToOrderedList(location, nearestNeighborhoods, neighborhood, n);
    }

    return getNearestNeighborhoods(n, nearestNeighborhoods);

  }

  private static void addToOrderedList(Location location,
      MinMaxPriorityQueue<DistanceNeighborhood> nearestNeighborhoods, Neighborhood neighborhood,
      int n) {

    double distanceBetween = location.distanceTo(neighborhood.location());
    DistanceNeighborhood distanceNeighborhood = new DistanceNeighborhood(distanceBetween,
        neighborhood);

    if (nearestNeighborhoods.size() < n) {
      nearestNeighborhoods.offer(distanceNeighborhood);
    } else if (nearestNeighborhoods.peekLast().getDistance() > distanceBetween) {
      nearestNeighborhoods.removeLast();
      nearestNeighborhoods.offer(distanceNeighborhood);
    }

  }

  private static List<Neighborhood> getNearestNeighborhoods(int n,
      MinMaxPriorityQueue<DistanceNeighborhood> nearestNeighborhoods) {

    List<Neighborhood> sortedNeighborhoods = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      sortedNeighborhoods.add(nearestNeighborhoods.poll().getNeighborhood());
    }

    return sortedNeighborhoods;
  }

}
