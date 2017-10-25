//
//  Distance.swift
//  nearby-neighborhoods
//
//  Created by Ekaterina Mril on 10/24/17.
//  Copyright © 2017 co.present. All rights reserved.
//

public class Distance {
  
  let distance: Double
  let neighborhood: Neighborhood
  
  public init(distance: Double, neighborhood: Neighborhood) {
    self.distance = distance
    self.neighborhood = neighborhood
  }
  
}

extension Distance: Equatable {
  
  public static func ==(lhs: Distance, rhs: Distance) -> Bool {
    return lhs.distance == rhs.distance
  }
  
}
