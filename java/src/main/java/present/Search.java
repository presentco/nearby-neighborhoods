package present;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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

    List<Neighborhood> newList = new ArrayList();

    for (Neighborhood neighborhood : Neighborhoods.ALL) {
      newList.add(neighborhood);
    }

    KDTree kdTree = new KDTree(newList, n);
    PriorityQueue<DistanceNeighborhood> nearestNeighborhoods = kdTree
        .findNearestNeighborhoods(location.latitude(), location.longitude());

    return getNearestNeighborhoods(n, nearestNeighborhoods);

  }


  private static List<Neighborhood> getNearestNeighborhoods(int n,
      PriorityQueue<DistanceNeighborhood> nearestNeighborhoods) {

    List<Neighborhood> sortedNeighborhoods = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      sortedNeighborhoods.add(nearestNeighborhoods.poll().getNeighborhood());
    }

    return sortedNeighborhoods;
  }

}
