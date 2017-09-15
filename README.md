# 🏘 Nearby Neighborhoods

Present is all about location, location, location! Our servers must efficiently find 
circles based on a user's location. During this challenge, instead of circles,
you'll search for _neighborhoods_ near a given location. 

## Instructions

Please fork this repo, and submit your solution in the form of a pull request.

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

__Distances Computed: 301__

4. What's the time complexity of `Search.near()`? It's reasonable assume that we'll query neighborhoods far more often than we'll add new neighborhoods. Feel free to pre-process the neighborhood data and make retrieval more efficient. Use whatever data structures, algorithms and libraries you like. Can you beat `O(n log(n))`? Can you achieve `O(1)`? Strut your stuff! 💃

__Time Complexity:
If k is total number of neighborhoods, and n is number of neighborhoods near the given location:
Initial population of heap: O(n)
Comparisons After: O((k-n)log(k))
Runtime: O(n + (k-n)log(k))__

5. This simple challenge included a few hundred neighborhoods. At Present, we need to sort through millions of user-generated circles in real time. What data structures and algorithms would you use to architect our backend?

__In the real time scenario, a hashmap or two dimensional graph data structure could be useful on the backend. With a graph, finding circles close to other circles could be done fairly quickly (since access for known values is O(1)). It might be possible to have the closest circle to the arbitrary point used to determine which other circles are close to that one. Another strategy could be to filter out circles that are obviously very far (using latitude and longtitude) before using the same heap based algorithm implemented.__


### Notes
- I liked using Intellij. Previously I did most of my java in a text editor and compiled from command line, but this was a vast improvement.
- This question took me a while to implement in Java - I'm not as familiar with the standard library. I can see how this would've taken less time in Python, although that would've involved far more type issues.
- The Guava documentation says its MinMaxPriorityQueue is not thread safe. Earlier in the process I was trying to use a thread safe hashmap, and then a thread safe version of a priority queue but while debugging I switched to this (I was looking for a bug that was hiding somewhere else). I think that as long as a user isn't permitted to do multiple searches at once, this *should* be okay, but a safer approach would be to see if there's a thread safe MinMaxPriorityQueue, or to wrap that in a method that can only be called by one thread at a time, or activates some mutex while a search is in progress.

-- GT
