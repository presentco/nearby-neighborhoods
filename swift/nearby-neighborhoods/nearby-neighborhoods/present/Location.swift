
import Foundation

/**
   A geographic location
*/
public class Location
{
  public let latitude: Double
  public let longitude: Double

  /**
   Constructs a new set of coordinates
    - parameter latitude: in degrees
    - parameter longitude: in degrees
  */
  public init(latitude: Double, longitude: Double) {
    self.latitude = latitude
    self.longitude = longitude
  }

  /**
    Computes the great-circle distance (https://en.wikipedia.org/wiki/Great-circle_distance)
    between this location and another in km.
   */
  public func distance(toLocation other: Location) -> Double {
    Location.distanceComputations.increment()
    fatalError("Please implement!")
  }

  static let distanceComputations = Counter()
}

extension FloatingPoint {
  var degreesToRadians: Self { return self * .pi / 180 }
  var radiansToDegrees: Self { return self * 180 / .pi }
}

extension Location: Hashable {
  public var hashValue: Int {
    return latitude.hashValue &* longitude.hashValue
  }
  
  public static func == (lhs: Location, rhs: Location) -> Bool {
    return lhs.latitude == rhs.latitude && lhs.longitude == rhs.longitude
  }
}
