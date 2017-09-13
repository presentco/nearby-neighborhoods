package present;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Searches {@link Neighborhoods#ALL}.
 */
public class Search {

  //Recommendation would be to use LRU cache with a max number of objects specified to avoid OOM issues
  private static final Map<Location, Map<Neighborhood, Double>> NEAREST_NEIGHBORHOOD_CACHE = new LinkedHashMap<>();

  /**
   * Finds the {@code n} neighborhoods nearest to the given location.
   *
   * @param location to search near
   * @param n number of neighborhoods to return
   * @return the {@code n} nearest neighborhoods, ordered nearest to farthest
   */
  public static List<Neighborhood> near(Location location, int n) {
    Map<Neighborhood, Double> sortedNeighborhoodMap = getSortedNeighborhoodMapFromCache(location);
    List<Neighborhood> nearestNeighborhoodList = new ArrayList<>();

    int counter = 0;
    for (Neighborhood neighborhood : sortedNeighborhoodMap.keySet()) {
      nearestNeighborhoodList.add(neighborhood);

      if (++counter >= n) {
        break;
      }
    }

      return nearestNeighborhoodList;
  }

  /**
   * Get a sorted map from cache where the Key is a Neighborhood and the Value is the distance to it. If not found, compute all the distances, add it to cache and return it.
   *
   * @param location to consider when computing distances to all other locations
   * @return sorted map of neighborhoods and the distance to them
   */
  private static Map<Neighborhood, Double> getSortedNeighborhoodMapFromCache(Location location) {
    Map<Neighborhood, Double> distanceToNeighborhoodsSortedMap = NEAREST_NEIGHBORHOOD_CACHE.get(location);

    if (distanceToNeighborhoodsSortedMap != null) {
      return distanceToNeighborhoodsSortedMap;
    }

    distanceToNeighborhoodsSortedMap = getSortedNeighborhoodMap(location);

    NEAREST_NEIGHBORHOOD_CACHE.put(location, distanceToNeighborhoodsSortedMap);

    return distanceToNeighborhoodsSortedMap;
  }

  /**
   *
   * Get a sorted map getting a map of all neighborhoods and distance and then using Java 8 streams to sort it by value (distance) into a LinkedHashMap
   *
   * @param location to consider when computing distances to all other locations
   * @return sorted map of neighborhoods and the distance to them
   */
  private static Map<Neighborhood, Double> getSortedNeighborhoodMap(Location location) {
    Map<Neighborhood, Double> distanceToNeighborhoodsMap = getNeighborhoodMap(location);

    return distanceToNeighborhoodsMap.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
  }

  /**
   *
   * Get a map of all neighborhoods and the distance to them from current location
   *
   * @param location to consider when computing distances to all other locations
   * @return map of neighborhoods and the distance to them
   */
  private static Map<Neighborhood, Double> getNeighborhoodMap(Location location) {
    Map<Neighborhood, Double> distanceToAllLocationsMap = new HashMap<>();

    for (Neighborhood neighborhood : Neighborhoods.ALL) {
      Location neighborhoodLocation = neighborhood.location();

      if (location == neighborhoodLocation) {
        // We do not want to record the current location.
        continue;
      }

      distanceToAllLocationsMap.put(neighborhood, location.distanceTo(neighborhoodLocation));
    }

    return distanceToAllLocationsMap;
  }
}
