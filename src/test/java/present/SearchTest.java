package present;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchTest {

  @Test public void nearPresent() {
    List<String> expected = ImmutableList.of(
        "Union Square",
        "Chinatown",
        "Financial District",
        "Nob Hill",
        "Tenderloin"
    );
    List<Neighborhood> actual = Search.near(new Location(37.7904251, -122.4059803), 5);
    assertEquals(expected, Lists.transform(actual, Neighborhood::name));
  }

  @Test public void nearThePaintedLadies() {
    List<String> expected = ImmutableList.of(
        "Haight-Ashbury",
        "North of Panhandle",
        "Anza Vista"
    );
    List<Neighborhood> actual = Search.near(new Location(37.7722899,-122.4421448), 3);
    assertEquals(expected, Lists.transform(actual, Neighborhood::name));
  }

  @Test public void nearLegionOfHonor() {
    List<String> expected = ImmutableList.of(
        "Seacliff",
        "Sunset District",
        "Richmond District",
        "Inner Sunset",
        "San Francisco",
        "Presidio Terrace",
        "Jordan Park",
        "Forest Hill"
    );
    List<Neighborhood> actual = Search.near(new Location(37.7747202, -122.5101659), 8);
    assertEquals(expected, Lists.transform(actual, Neighborhood::name));
  }

  @Test public void nearPresentSorted() {
    List<String> expected = ImmutableList.of(
            "Union Square",
            "Chinatown",
            "Financial District",
            "Nob Hill",
            "Tenderloin"
    );
    List<Neighborhood> actual = Search.nearSorted(new Location(37.7904251, -122.4059803), 5);
    assertEquals(expected, Lists.transform(actual, Neighborhood::name));
  }

  @Test public void nearThePaintedLadiesSorted() {
    List<String> expected = ImmutableList.of(
            "Haight-Ashbury",
            "North of Panhandle",
            "Anza Vista"
    );
    List<Neighborhood> actual = Search.nearSorted(new Location(37.7722899,-122.4421448), 3);
    assertEquals(expected, Lists.transform(actual, Neighborhood::name));
  }

  @Test public void nearLegionOfHonorSorted() {
    List<String> expected = ImmutableList.of(
            "Seacliff",
            "Sunset District",
            "Richmond District",
            "Inner Sunset",
            "San Francisco",
            "Presidio Terrace",
            "Jordan Park",
            "Forest Hill"
    );
    List<Neighborhood> actual = Search.nearSorted(new Location(37.7747202, -122.5101659), 8);
    assertEquals(expected, Lists.transform(actual, Neighborhood::name));
  }

  @Before public void resetCounter() {
    Location.distanceComputations.set(0);
  }

  @After public void printCounter() {
    System.out.printf("Computed %s distances.\n", Location.distanceComputations.get());
  }
}
