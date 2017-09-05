# Nearby Neighborhoods

Present is all about location, location, location! Our servers must efficiently find 
circles of interest based on a user's location. During this challenge, instead of circles,
you'll search for _neighborhoods_ near a given location.

## Instructions

**Please fork this repo, and submit your solution in the form of a pull request.** You can build
and run the code with [Maven](https://maven.apache.org/) or just about any IDE. Feel free
to use and demonstrate your knowledge of 3rd party libraries. We follow
[Google's Java Style Guide](https://google.github.io/styleguide/javaguide.html).

1. Make [`Location.distanceTo()`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/main/java/present/Location.java)
compute the [great-circle distance](https://en.wikipedia.org/wiki/Great-circle_distance)
so that [`LocationTest`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/test/java/present/LocationTest.java)
passes.

2. Make [`Search.near()`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/main/java/present/Search.java)
return the neighborhoods closest to a location so that [`SearchTest`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/test/java/present/SearchTest.java)
passes.

3. Computing distances can add up! Cache the results of your distance computations. 
How many computations did you save? How much time did you save?

4. What's the time complexity of your solution? Can you beat O(n)? Our example includes a
few hundred neighborhoods. Can your solution scale to support millions?
