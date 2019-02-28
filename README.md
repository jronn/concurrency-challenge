# Concurrency Challenge

## Exercise 6 - Modernize Tests

This exercise has the same code base as the previous, but let us refactor the tests instead.

They are not-so-very-modern and there are a lot that can be improved. Lets have a look at them.

[DangerousAgeCacheTest](src/test/java/sven/workshop/concurrency/DangerousAgeCacheTest.java) the expected result is a crash.

[UglyAgeCacheTest](src/test/java/sven/workshop/concurrency/UglyAgeCacheTest.java) would run forever if there wasn't a timeout included. 


Here is what to do: 

  * Refactor with modern (Java 8 or later) features
  
    * Can the `UncaughtExceptionHandler` be replaced?
  
    * How can we replace the `Timer`? Do we need it at all?
    
    * Can the creation of the worker threads be improved?
    
    * Why do we have a `CountdownLatch`?
    
 Hints. Even though you are free to make your own implementation, here are some features that might help you on the way.
 
   * [Thread Pools](https://www.baeldung.com/thread-pool-java-and-guava)
   
   * Stream Parallel (Should we use it and if so, how?) [The pitfall] (https://blog.krecan.net/2014/03/18/how-to-specify-thread-pool-for-java-8-parallel-streams/)
   
   * Runnable vs Callable vs Future
   
   * CyclicBarrier vs CountdownLatch

As usual there will be a meeting to go through the code.

