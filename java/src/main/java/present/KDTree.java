package present;

import com.google.common.collect.MinMaxPriorityQueue;

import static java.lang.Math.toRadians;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class KDTree {

  private static final int K = 3;
  private final Node tree;
  MinMaxPriorityQueue<DistanceNeighborhood> nearestNodes;

  KDTree(@Nonnull final List<Neighborhood> neighborhoods, int elementsNeeded) {
    nearestNodes = MinMaxPriorityQueue.maximumSize(elementsNeeded).create();

    final List<Node> nodes = new ArrayList<>(neighborhoods.size());
    for (Neighborhood neighborhood : neighborhoods) {
      nodes.add(new Node(neighborhood.name(), neighborhood.location()));
    }

    tree = buildTree(nodes, 0);
  }

  @Nullable
  MinMaxPriorityQueue<DistanceNeighborhood> findNearestNeighborhoods(final double latitude,
      final double longitude) {
    Node initialTarget = new Node(latitude, longitude);
    findNearest(tree, initialTarget, 0);

    return nearestNodes;
  }


  private Node findNearest(final Node current, final Node target, final int depth) {

    addNodeToQueue(current, target);

    final int axis = depth % K;
    final int direction = getComparator(axis).compare(target, current);
    final Node next = (direction < 0) ? current.left : current.right;
    final Node other = (direction < 0) ? current.right : current.left;
    Node best = (next == null) ? current : findNearest(next, target, depth + 1);

    if (current.euclideanDistanceCalc(target) < best.euclideanDistanceCalc(target)) {

      if (next.location.distanceTo(current.location) < nearestNodes.peekLast().getDistance()) {
        findNearest(next, target, depth + 1);
      } else {
        best = current;
      }
    }
    if (other != null) {
      if (current.verticalDistance(target, axis) < best.euclideanDistanceCalc(target)) {
        final Node possibleBest = findNearest(other, target, depth + 1);
        if (possibleBest.euclideanDistanceCalc(target) < best.euclideanDistanceCalc(target)) {

          if (possibleBest.location.distanceTo(current.location) < nearestNodes.peekLast()
              .getDistance()) {
            findNearest(possibleBest, target, depth + 1);
          } else {
            best = possibleBest;
          }

        }

      }
    }

    return best;
  }

  private void addNodeToQueue(Node current, Node target) {

    boolean alreadyPresent = false;
    double distanceBetween = current.location.distanceTo(target.location);

    DistanceNeighborhood distanceNeighborhood = new DistanceNeighborhood(distanceBetween,
        new Neighborhood(current.name, current.location));

    for (DistanceNeighborhood node : nearestNodes) {
      if (node.compareTo(distanceNeighborhood) == 0) {
        alreadyPresent = true;
      }
    }

    if (!alreadyPresent) {
      nearestNodes.offer(distanceNeighborhood);
    }

  }

  @Nullable
  private static Node buildTree(final List<Node> items, final int depth) {
    if (items.isEmpty()) {
      return null;
    }

    items.sort(getComparator(depth % K));
    final int index = items.size() / 2;
    final Node root = items.get(index);
    root.left = buildTree(items.subList(0, index), depth + 1);
    root.right = buildTree(items.subList(index + 1, items.size()), depth + 1);
    return root;
  }

  private static class Node {

    Node left;
    Node right;
    Location location;
    final double[] point = new double[K];
    String name;

    Node(final double latitude, final double longitude) {
      this.location = new Location(latitude, longitude);
      point[0] = (cos(toRadians(latitude)) * cos(toRadians(longitude)));
      point[1] = (cos(toRadians(latitude)) * sin(toRadians(longitude)));
      point[2] = (sin(toRadians(latitude)));
    }

    Node(final String name, final Location location) {
      this(location.latitude(), location.longitude());
      this.location = location;
      this.name = name;
    }

    double euclideanDistanceCalc(final Node that) {
      final double x = this.point[0] - that.point[0];
      final double y = this.point[1] - that.point[1];
      final double z = this.point[2] - that.point[2];

      return x * x + y * y + z * z;
    }

    double verticalDistance(final Node that, final int axis) {
      final double d = this.point[axis] - that.point[axis];
      return d * d;
    }

  }

  private static Comparator<Node> getComparator(final int i) {
    return NodeComparator.values()[i];
  }

  private enum NodeComparator implements Comparator<Node> {
    x {
      @Override
      public int compare(final Node a, final Node b) {
        return Double.compare(a.point[0], b.point[0]);
      }
    },
    y {
      @Override
      public int compare(final Node a, final Node b) {
        return Double.compare(a.point[1], b.point[1]);
      }
    },
    z {
      @Override
      public int compare(final Node a, final Node b) {
        return Double.compare(a.point[2], b.point[2]);
      }
    }
  }
}
