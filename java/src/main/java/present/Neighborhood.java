package present;

/**
 * A neighborhood comprised of a name and location
 */
public class Neighborhood {

  private final String name;
  private final Location location;

  /**
   * Constructs a new neighborhood
   *
   * @param name of the neighborhood
   * @param location of the neighborhood
   */
  public Neighborhood(String name, Location location) {
    this.name = name;
    this.location = location;
  }

  /** Returns the name of this neighborhood. */
  public String name() {
    return name;
  }

  /** Returns the location of this neighborhood. */
  public Location location() {
    return location;
  }
}
