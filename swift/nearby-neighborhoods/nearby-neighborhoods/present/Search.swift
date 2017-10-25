
/**
   Searches Neighborhoods.ALL
*/
import Foundation

public class Search {

  /**
    Finds the {@code n} neighborhoods nearest to the given location.
      - parameter location: to search near
      - parameter n: number of neighborhoods to return
      - returns: the n nearest neighborhoods, ordered nearest to farthest
   */
  public static func near(location: Location, n: Int) -> [Neighborhood] {
    var heap = Heap<Distance>(sort: { (a, b) -> Bool in
      return a.distance > b.distance
    })
    
    for neighborhood in Neighborhoods.ALL {
      let distance = Distance(distance: location.distance(toLocation: neighborhood.location), neighborhood: neighborhood)
      
      if heap.count < n  {
        heap.insert(distance)
      } else {
        if let peakDistance = heap.peek()?.distance, distance.distance < peakDistance {
          heap.remove()
          heap.insert(distance)
        }
      }
    }
    
    let neighborhoods =  heap.elements.sorted { (a, b) -> Bool in
      return a.distance < b.distance
      }.map {$0.neighborhood}
    
    return neighborhoods
  }
  
}
