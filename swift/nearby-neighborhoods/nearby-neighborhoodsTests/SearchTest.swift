//
//  nearby_neighborhoodsTests.swift
//  nearby-neighborhoodsTests
//

import XCTest
@testable import nearby_neighborhoods

class SearchTest : XCTestCase {
    
  func testNearPresent() {
    let expected = [
        "Union Square",
        "Chinatown",
        "Financial District",
        "Nob Hill",
        "Tenderloin"
    ]
    let actual = Search.near(location: Location(latitude: 37.7904251, longitude: -122.4059803), n: 5)
    XCTAssertEqual(expected, actual.map { $0.name })
  }

  func testNearThePaintedLadies() {
    let expected = [
        "Haight-Ashbury",
        "North of Panhandle",
        "Anza Vista"
    ]
    let actual = Search.near(location: Location(latitude: 37.7722899,longitude: -122.4421448), n: 3)
    XCTAssertEqual(expected, actual.map { $0.name })
  }

  func testNearLegionOfHonor() {
    let expected = [
        "Seacliff",
        "Sunset District",
        "Richmond District",
        "Inner Sunset",
        "San Francisco",
        "Presidio Terrace",
        "Jordan Park",
        "Forest Hill"
    ]
    let actual = Search.near(location: Location(latitude: 37.7747202, longitude: -122.5101659), n: 8)
    XCTAssertEqual(expected, actual.map { $0.name })
  }

  override func setUp() {
    super.setUp()
    Location.distanceComputations.reset()
  }
    
  override func tearDown() {
    print("Computed \(Location.distanceComputations.value) distances.")
    super.tearDown()
  }
}
