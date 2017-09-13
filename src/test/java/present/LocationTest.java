package present;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {
  
  @Test public void distanceTo() {
    Location sf = new Location(37.7577627,-122.4727051);
    Location nyc = new Location(40.6976633,-74.1201054);
    assertEquals(4125, sf.distanceTo(nyc), 1);
  }
}
