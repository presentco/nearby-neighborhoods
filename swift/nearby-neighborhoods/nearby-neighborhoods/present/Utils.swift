//
//  Utils.swift
//  nearby-neighborhoods
//

import Foundation

public class Counter {
  private var queue = DispatchQueue(label: "counter queue")
  public private (set) var value = 0
  
  public func reset() {
    queue.async { self.value = 0 }
  }
  
  public func increment() {
    queue.async { self.value += 1 }
  }
}
