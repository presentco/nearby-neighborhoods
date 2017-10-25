
import Foundation
import MapKit

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
    
    return greatCircleDistance(lat1: latitude, lon1: longitude, lat2: other.latitude, lon2: other.longitude)
  }
  
  func greatCircleDistance(lat1:Double, lon1:Double, lat2:Double, lon2:Double) -> Double {
    let lat1rad = lat1.degreesToRadians
    let lon1rad = lon1.degreesToRadians
    let lat2rad = lat2.degreesToRadians
    let lon2rad = lon2.degreesToRadians
    
    let dLat = lat2rad - lat1rad
    let dLon = lon2rad - lon1rad
    let a = sin(dLat/2) * sin(dLat/2) + sin(dLon/2) * sin(dLon/2) * cos(lat1rad) * cos(lat2rad)
    let c = 2 * asin(sqrt(a))
    let R = 6372.8
    
    return R * c
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
