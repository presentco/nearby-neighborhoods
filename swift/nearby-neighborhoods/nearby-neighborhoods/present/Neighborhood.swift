
/**
  A neighborhood comprised of a name and location
*/
public class Neighborhood {

  public let name: String
  public let location: Location

  /**
   Constructs a new neighborhood
    - parameter name: of the neighborhood
    - parameter location: of the neighborhood
  */
  public init(name: String, location: Location) {
    self.name = name
    self.location = location
  }
}

extension Neighborhood : Hashable {
  public var hashValue: Int {
    return name.hashValue &* location.hashValue
  }
  
  public static func == (lhs: Neighborhood, rhs: Neighborhood) -> Bool {
    return lhs.name == rhs.name && lhs.location == rhs.location
  }
}
