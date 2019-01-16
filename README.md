# Concurrency Challenge

## Exercise 5 - Get rid of Synchronized

Surely, I hate that word!

There are two implementations of the `AgeCache` interface and we have tests for both classes. 

[DangerousAgeCache](src/main/java/sven/workshop/concurrency/DangerousAgeCache.java) will cause problems. As you can see in the test [DangerousAgeCacheTest](src/test/java/sven/workshop/concurrency/DangerousAgeCacheTest.java) the expected result is a crash.

[UglyAgeCache](src/main/java/sven/workshop/concurrency/UglyAgeCache.java) on the other hand will run without race conditions, and the test [UglyAgeCacheTest](src/test/java/sven/workshop/concurrency/UglyAgeCacheTest.java) would run forever if there wasn't a timeout included. It has the ugly keyword though...

Here is what to do: 

  * Read the tests and make sure you understand what they are doing
  
    * What is an `UncaughtExceptionHandler`?
  
    * What is a `Timer` and how does it work?
    
    * What is the `Timer`-logic doing?
    
    * `Awaitility`, what is it and why do we use it?
    
  * Why is one test expected to crash and why is the other not?
  
    * Bonus task; What happens if you remove `volatile` in `DangerousAgeCache`? If you test it, make sure you understand the outcome
    
Now, when you sorted out all of the above, your mission is to create a new implementation of the `AgeCache` interface *WITHOUT* the `synchronized` keyword. Hint: [java.util.concurrent.locks.Lock implementations](https://www.baeldung.com/java-concurrent-locks) are handy here.

As usual there will be a meeting to go through the code.

