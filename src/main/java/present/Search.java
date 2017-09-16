package present;

import com.google.common.collect.Lists;

import java.util.*;

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

    // Head is biggest distance in queue
    PriorityQueue<NeighborhoodDistance> priorityQueue = new PriorityQueue<>((o1, o2) -> Double.compare(o2.distance(), o1.distance()));

    for (Neighborhood neighborhood : Neighborhoods.ALL) {
      addToQueueIfNearby(location, n, priorityQueue, neighborhood);
    }

    return toNeighborhoodListSortedByDistance(priorityQueue);
  }

  private static List<Neighborhood> toNeighborhoodListSortedByDistance(PriorityQueue<NeighborhoodDistance> priorityQueue) {
    List<NeighborhoodDistance> nearestNeighborhoodDistances = Lists.newArrayList(priorityQueue);
    nearestNeighborhoodDistances.sort(Comparator.comparingDouble(NeighborhoodDistance::distance));
    return Lists.transform(nearestNeighborhoodDistances, NeighborhoodDistance::neighborhood);
  }

  /**
   * Add a neighborhood to the queue if it's not full yet, or if it's closer than the current worst neighborhood.
   * If the queue is full, evict the previous furthest neighborhood when we add a new one.
   */
  private static void addToQueueIfNearby(Location location, int n, PriorityQueue<NeighborhoodDistance> priorityQueue, Neighborhood neighborhood) {
    double distance = location.distanceTo(neighborhood.location());
    if (priorityQueue.size() < n) {
      priorityQueue.add(new NeighborhoodDistance(distance, neighborhood));
    } else if (priorityQueue.peek().distance() > distance) {
      priorityQueue.remove(); // peek() and remove() are both O(1)
      priorityQueue.add(new NeighborhoodDistance(distance, neighborhood));
    }
  }

  public static List<Neighborhood> nearSorted(Location location, int n) {
    PriorityQueue<NeighborhoodDistance> priorityQueue = new PriorityQueue<>((o1, o2) -> Double.compare(o2.distance(), o1.distance()));

    // Multiplying by -1 assumes the binary search returns a target index and never a real match
    int closestLatitudeIndex = -Collections.binarySearch(SortedNeighborhoods.LOCATIONS_BY_LATITUDE, location, Comparator.comparingDouble(Location::latitude));
    int closestLongitudeIndex = -Collections.binarySearch(SortedNeighborhoods.LOCATIONS_BY_LONGITUDE, location, Comparator.comparingDouble(Location::longitude));

    // TODO: Do in a loop, increasing the search radius number
    int searchRadiusNumber = 5 * n;
    List<Neighborhood> sublist = getAdjacentSublist(SortedNeighborhoods.BY_LATITUDE, closestLatitudeIndex, searchRadiusNumber);
    HashSet<Neighborhood> closestNeighborhoodsByLatitude = new HashSet<Neighborhood>(sublist); // O(n)

    getAdjacentSublist(SortedNeighborhoods.BY_LONGITUDE, closestLongitudeIndex, searchRadiusNumber).forEach(neighborhood -> {
      if (closestNeighborhoodsByLatitude.contains(neighborhood)) {
        addToQueueIfNearby(location, n, priorityQueue, neighborhood);
      } else {
        // add to a list to reconsider on the next pass with an expanded search radius
      }
    });

    return toNeighborhoodListSortedByDistance(priorityQueue);
  }

  /**
   * Get a sublist of the input list by taking n on either side of the target index. If not possible, take 2n including
   * the target index and beginning or end of the list (whichever is a bound).  If list's size is smaller than
   * 2n just return the list.
   *
   * @param input
   * @param targetIndex
   * @param n
   * @param <T>
   * @return
   */
  private static <T> List<T> getAdjacentSublist(List<T> input, int targetIndex, int n) {
    if (input.size() < 2 * n) return input;
    int upperBound;
    int lowerBound;
    if (targetIndex + n > input.size()) {
      upperBound = input.size();
      lowerBound = input.size() - n;
    } else if (targetIndex - n < 0) {
      lowerBound = 0;
      upperBound = 2 * n;
    } else {
      upperBound = targetIndex + n;
      lowerBound = targetIndex - n;
    }
    return input.subList(lowerBound, upperBound);
  }
}
