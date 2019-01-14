package sven.workshop.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AgeCacheTest {

  final static int THREADS = 2;
  final static CountDownLatch latch = new CountDownLatch(THREADS);
  final static DangerousAgeCache cache = new DangerousAgeCache(1000);
  volatile AtomicBoolean threadFailed = new AtomicBoolean(false);

  @Test()
  public void expectAssertionError() {

    final Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(final Thread t, final Throwable e) {
        System.out.println("Dedd");
        threadFailed.set(true);
      }
    };

    for (int i = 0; i < THREADS; i++) {
      final Thread t = thread();
      t.setUncaughtExceptionHandler(exceptionHandler);
      t.start();
    }

    Awaitility.await().atMost(Duration.TEN_SECONDS).untilTrue(threadFailed);
    Assertions.assertTrue(threadFailed.get());
  }

  private Thread thread() {

    return new Thread() {

      @Override
      public void run() {
        latch.countDown();
        try {
          latch.await();
          while (true) {
            cache.increase(1);
            cache.decrease(1);
          }
        } catch (final InterruptedException e) {
          return;
        }
      }
    };

  }
}
