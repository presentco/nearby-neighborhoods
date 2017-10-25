//
//  nearby_neighborhoodsTests.swift
//  nearby-neighborhoodsTests
//

import XCTest
@testable import nearby_neighborhoods

class LocationTest : XCTestCase {
    
  func testDistanceTo() {
    let sf = Location(latitude: 37.7577627,longitude: -122.4727051)
    let nyc = Location(latitude: 40.6976633,longitude: -74.1201054)
    let distance = sf.distance(toLocation: nyc).rounded()
    XCTAssertEqualWithAccuracy(4125, distance, accuracy: 1.0)
  }
    
}
