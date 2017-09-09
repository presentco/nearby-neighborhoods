package present;

import java.util.*;

/**
 * Searches {@link Neighborhoods#ALL}.
 */
public class Search {

    /**
     * Finds the {@code n} neighborhoods nearest to the given location.
     *
     * @param location to search near
     * @param n        number of neighborhoods to return
     * @return the {@code n} nearest neighborhoods, ordered nearest to farthest
     */
    public static List<Neighborhood> near(Location location, int n) {
        Map<Neighborhood, Double> distanceCache = new HashMap<>();
        final int neighborhoodsSize = Neighborhoods.ALL.size();
        final double[] dist = new double[neighborhoodsSize];
        int i = 0;
        for (Neighborhood neighborhood : Neighborhoods.ALL) {
            dist[i] = neighborhood.location().distanceTo(location);
            distanceCache.put(neighborhood, dist[i]);
            i++;
        }
        final double nthMinDistance = nthSmallestDistance(dist, 0, neighborhoodsSize - 1, n);

        TreeMap<Double, Neighborhood> result = new TreeMap<>();
        int j = 0;
        for (Map.Entry<Neighborhood, Double> neighborhood : distanceCache.entrySet()) {
            if (j >= n) {
                break;
            }
            if (neighborhood.getValue() <= nthMinDistance) {
                result.put(neighborhood.getValue(), neighborhood.getKey());
                j++;
            }
        }

        return new LinkedList<>(result.values());
    }

    private static double nthSmallestDistance(double[] distances, int left, int right, int n) {
        //System.out.println(left + " "+ right);
        if (left == right) {
            return distances[left];
        }
        if (left < right) {
            final int pivot = randomizedPartition(distances, left, right);
            final int pivotIndex = pivot - left + 1;
            if (n == pivotIndex) {
                return distances[pivot];
            } else if (n < pivotIndex) {
                return nthSmallestDistance(distances, left, pivot - 1, n);
            } else {
                return nthSmallestDistance(distances, pivot + 1, right, n - pivotIndex);
            }
        } else {
            return Double.MIN_VALUE;
        }
    }

    private static void swap(double[] array, int index1, int index2) {
        final double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static int partition(double[] array, int left, int right) {
        final double pivot = array[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }

    private static int randomizedPartition(double[] array, int left, int right) {
        final int pivot = (int) Math.round(left + Math.random() * (right - left));
        swap(array, pivot, right);
        return partition(array, left, right);
    }
}