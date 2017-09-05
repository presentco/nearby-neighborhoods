package present;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchTest {

  private static final Location PRESENT = new Location(37.7904251,-122.4059803);

  @Test public void nearPresent() {
    List<String> expected = ImmutableList.of(
        "Union Square",
        "Chinatown",
        "Financial District",
        "Nob Hill",
        "Tenderloin"
    );
    List<Neighborhood> actual = Search.near(PRESENT, 5);
    assertEquals(expected, Lists.transform(actual, Neighborhood::name));
  }

  @Before public void resetCounter() {
    Location.distanceComputations.set(0);
  }

  @After public void printCounter() {
    System.out.printf("Computed %s distances.", Location.distanceComputations.get());
  }
}
