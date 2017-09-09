package present;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Searches {@link Neighborhoods#ALL}.
 */
public class Search {

  private static final Map<Location, Map<Double, Neighborhood>> DISTANCE_FROM_LOCATION_TO_NEIGHBORHOODSSORTED_MAP_CACHE = new LinkedHashMap<>();

  /**
   * Finds the {@code n} neighborhoods nearest to the given location.
   *
   * @param location to search near
   * @param n number of neighborhoods to return
   * @return the {@code n} nearest neighborhoods, ordered nearest to farthest
   */
  public static List<Neighborhood> near(Location location, int n) {
    Map<Double, Neighborhood> distanceToNeighborhoodsSortedMap = getSortedDistanceToAllLocationsMap(location);
    List<Neighborhood> nearestNeighborhoodList = new ArrayList<>();

    int counter = 0;
    for (Neighborhood neighborhood : distanceToNeighborhoodsSortedMap.values()) {
      nearestNeighborhoodList.add(neighborhood);

      if (++counter >= n) {
        break;
      }
    }

      return nearestNeighborhoodList;
  }

  private static Map<Double, Neighborhood> getSortedDistanceToAllLocationsMap(Location location) {
    Map<Double, Neighborhood> distanceToNeighborhoodsSortedMap = DISTANCE_FROM_LOCATION_TO_NEIGHBORHOODSSORTED_MAP_CACHE.get(location);

    if (distanceToNeighborhoodsSortedMap != null) {
      return distanceToNeighborhoodsSortedMap;
    }

    distanceToNeighborhoodsSortedMap = getSortedNeighborhoodMapFromLocation(location);

    DISTANCE_FROM_LOCATION_TO_NEIGHBORHOODSSORTED_MAP_CACHE.put(location, distanceToNeighborhoodsSortedMap);

    return distanceToNeighborhoodsSortedMap;
  }

  private static Map<Double, Neighborhood> getSortedNeighborhoodMapFromLocation (Location location) {
    Map<Double, Neighborhood> distanceToNeighborhoodsMap = getDistanceToNeighborhoodsMap(location);

    return distanceToNeighborhoodsMap.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
  }

  private static Map<Double, Neighborhood> getDistanceToNeighborhoodsMap(Location location) {
    Map<Double, Neighborhood> distanceToAllLocationsMap = new HashMap<>();

    for (Neighborhood neighborhood : Neighborhoods.ALL) {
      Location neighborhoodLocation = neighborhood.location();

      if (location == neighborhoodLocation) {
        // We do not want to record the current location.
        continue;
      }

      distanceToAllLocationsMap.put(location.distanceTo(neighborhoodLocation), neighborhood);
    }

    return distanceToAllLocationsMap;
  }
}
