package present;

import java.util.*;
import com.google.common.collect.MinMaxPriorityQueue;

/**
 * Searches {@link Neighborhoods#ALL}.
 */
public class Search {

  /** Priority Queue to determine which neighborhoods are closest. */
  private static MinMaxPriorityQueue<NeighborhoodQueueObject> nearest;

  /**
   * NeighborhoodQueueObject
   * Temporary object used with Priority Queue to sort Neighborhoods by distance from any point.
   */
  public static class NeighborhoodQueueObject implements Comparable<NeighborhoodQueueObject>{
    public double distFromLocation;
    public Neighborhood neighborhood;

    public NeighborhoodQueueObject(double d, Neighborhood n) {
      this.distFromLocation = d;
      this.neighborhood = n;
    }

    public Neighborhood neighborhood() {
      return this.neighborhood;
    }

    public double distFromLocation() {
      return this.distFromLocation;
    }

    @Override
    public int compareTo(NeighborhoodQueueObject o) {
      if (this.distFromLocation < o.distFromLocation) {
        return -1;
      } else if (this.distFromLocation > o.distFromLocation) {
        return 1;
      } else {
        return 0;
      }
    }
  }

  /**
   * Finds the {@code n} neighborhoods nearest to the given location.
   *
   * @param location to search near
   * @param n number of neighborhoods to return
   * @return the {@code n} nearest neighborhoods, ordered nearest to farthest
   */
  public static List<Neighborhood> near(Location location, int n) {
    nearest = MinMaxPriorityQueue.maximumSize(n).create();
    List<Neighborhood> nearestList = new LinkedList<Neighborhood>();
    double distBetween;

    for (Neighborhood neigh: Neighborhoods.ALL) {
      distBetween = location.distanceTo(neigh.location());
      if (nearest.size() < n) {
        NeighborhoodQueueObject neighborQueueObject = new NeighborhoodQueueObject(distBetween, neigh);
        nearest.offer(neighborQueueObject);
      }
      else {
        if (nearest.peekLast().distFromLocation() > distBetween) {
          nearest.pollLast();
          NeighborhoodQueueObject neighborQueueObject = new NeighborhoodQueueObject(distBetween, neigh);
          nearest.offer(neighborQueueObject);
        }
      }
    }

    for (int i = 0; i < n; i++) {
      nearestList.add(nearest.poll().neighborhood());
    }
    return nearestList;
  }

}
