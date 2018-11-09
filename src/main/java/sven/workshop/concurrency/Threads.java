package sven.workshop.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CountDownLatch;

/**
 * If you want to test how many threads you can get, run this. And no, thou shall NOT use that many threads! 
 *
 */
public class Threads {

  public static void main(String[] args) throws InterruptedException {
    final AtomicInteger threadsCreated = new AtomicInteger(0);
    while (true) {
      final CountDownLatch latch = new CountDownLatch(1);
      new Thread() {
        {
          start();
        }

        public void run() {
          latch.countDown();
          synchronized (this) {
            System.out.println("threads created: " + threadsCreated.incrementAndGet());
            try {
              wait();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          }
        }
      };
      latch.await();
    }
  }
}
