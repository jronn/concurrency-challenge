# Concurrency Challenge

## Exercise 6 - Modernize Tests

This exercise has the same code base as the previous, but let us refactor the tests instead.

They are not-so-very-modern and there are a lot of things that can be improved.

[DangerousAgeCacheTest](src/test/java/sven/workshop/concurrency/DangerousAgeCacheTest.java)

 * The expected result is a crash. When in time we can only guess
 
 * The output is there to show users that work is ongoing (it's a demo case, not really a test). It should not affect the test itself and you should keep it
 
 * There should be N number of threads in use

[UglyAgeCacheTest](src/test/java/sven/workshop/concurrency/UglyAgeCacheTest.java) 

 * Would run forever if there wasn't a timeout included
 
 * Same here, keep the output somehow
 
 * There should be N number of threads in use

Here is what to do: 

  * Refactor with modern (Java 8 or later) features
  
    * Can the `UncaughtExceptionHandler` be replaced?
  
    * How can we replace the `Timer`? Do we need it at all?
    
    * Can the creation of the worker threads be improved?
    
    * Why do we have a `CountdownLatch`?
    
 Hints. Even though you are free to make your own implementation, here are some features that might help you on the way.
 
   * [Thread Pools](https://www.baeldung.com/thread-pool-java-and-guava)
   
   * Stream Parallel (Should we use it and if so, how?) [The pitfall](https://blog.krecan.net/2014/03/18/how-to-specify-thread-pool-for-java-8-parallel-streams/)
   
   * Runnable vs Callable vs Future [Some intro reading here](https://www.baeldung.com/java-runnable-callable)
   
   * CyclicBarrier vs CountdownLatch [SO explains](https://stackoverflow.com/questions/34209257/why-does-java-have-both-countdownlatch-and-cyclicbarrier)

As usual there will be a meeting to go through the code.

