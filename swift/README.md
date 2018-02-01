# 🏘 Nearby Neighborhoods

Present is all about location, location, location! Our servers must efficiently find 
circles based on a user's location. During this challenge, instead of circles,
you'll search for _neighborhoods_ near a given location. 

## Instructions

Please fork this repo, and submit your solution in the form of a pull request.

* You can build and run the code with [XCode](https://developer.apple.com/xcode/) But feel free to use any IDE or editor that helps. We ❤️ [AppCode](https://www.jetbrains.com/objc/).
* Feel free to use and demonstrate your knowledge of 3rd party libraries!
* Our servers are multi-threaded. Your code should be scaleable and safe for concurrent use.
* We write code once but read it many times. Please consider the maintainability of your solution.

Now for the challenge:

1. Make [`Location.distanceTo()`](https://github.com/presentco/nearby-neighborhoods/blob/master/swift/nearby-neighborhoods/nearby-neighborhoods/present/Location.swift)
compute the [great-circle distance](https://en.wikipedia.org/wiki/Great-circle_distance)
so that [`LocationTest`](https://github.com/presentco/nearby-neighborhoods/blob/master/swift/nearby-neighborhoods/nearby-neighborhoodsTests/LocationTest.swift)
passes.

2. Make [`Search.near()`](https://github.com/presentco/nearby-neighborhoods/blob/master/swift/nearby-neighborhoods/nearby-neighborhoods/present/Search.swift)
return the neighborhoods closest to a location so that [`SearchTest`](https://github.com/presentco/nearby-neighborhoods/blob/master/swift/nearby-neighborhoods/nearby-neighborhoodsTests/SearchTest.swift)
passes.

3. Computing distances can add up! Make sure we're not performing duplicate distance computations during `Search.near()`. How many distances do we compute?

4. What's the time complexity of `Search.near()`? It's reasonable assume that we'll query neighborhoods far more often than we'll add new neighborhoods. Feel free to pre-process the neighborhood data and make retrieval more efficient. Use whatever data structures, algorithms and libraries you like. Can you beat `O(n log(n))`? Can you achieve `O(1)`? Strut your stuff! 💃

5. Implement a map UI that plots the 5 neighborhoods closest to the center of the map. When the user moves the map, plot the 5 neighborhoods that are closest to the new center.
