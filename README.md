# 🏘 Nearby Neighborhoods

Present is all about location, location, location! Our servers must efficiently find 
circles based on a user's location. During this challenge, instead of circles,
you'll search for _neighborhoods_ near a given location. 

## Instructions

**Please fork this repo, and submit your solution in the form of a pull request.** 

* You can build and run the code with [Maven](https://maven.apache.org/) or just about any IDE. We ❤️ [IntelliJ](https://www.jetbrains.com/idea/).
* Feel free to use and demonstrate your knowledge of 3rd party libraries!
* Our servers are multi-threaded. Your code should be scaleable and safe for concurrent use.
* We follow [Google's Java Style Guide](https://google.github.io/styleguide/javaguide.html). Please do the same.

Now for the challenge:

1. Make [`Location.distanceTo()`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/main/java/present/Location.java)
compute the [great-circle distance](https://en.wikipedia.org/wiki/Great-circle_distance)
so that [`LocationTest`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/test/java/present/LocationTest.java)
passes.

2. Make [`Search.near()`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/main/java/present/Search.java)
return the neighborhoods closest to a location so that [`SearchTest`](https://github.com/presentco/nearby-neighborhoods/blob/master/src/test/java/present/SearchTest.java)
passes.

3. Computing distances can add up! Make sure we're not performing duplicate distance computations during `Search.near()`. How many distances do we compute?

4. What's the time complexity of `Search.near()`? Can you beat O(n log(n))? Note: Indexing beforehand is allowed. Can you beat O(n)? Strut your stuff! 💃

5. This simple challenge included a few hundred neighborhoods. At Present, we need to sort through millions of user-generated circles in real time. What data structures and algorithms would you use to architect our backend?
