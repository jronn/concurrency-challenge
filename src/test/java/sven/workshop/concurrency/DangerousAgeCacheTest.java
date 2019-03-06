package sven.workshop.concurrency;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;

public class DangerousAgeCacheTest {

  private static final Logger LOG = Logger.getAnonymousLogger();

  private static final int THREADS = 2;
  private static final CountDownLatch latch = new CountDownLatch(THREADS);
  private static final AgeCache cache = new DangerousAgeCache(1000);

  @Test()
  void expectAssertionError() {
    final var timerService = Executors.newSingleThreadScheduledExecutor();
    timerService.scheduleAtFixedRate(
        () -> LOG.info(String.valueOf(cache.getAge())), 5L, 5L, TimeUnit.MILLISECONDS);

    final var executorService = Executors.newFixedThreadPool(THREADS);
    final List<Future> threadExecutionResults = IntStream.range(0, THREADS)
        .mapToObj(i -> executorService.submit(this::doStuffWithCache))
        .collect(Collectors.toList());

    Awaitility.await()
        .atMost(Duration.TEN_SECONDS)
        .untilTrue(new AtomicBoolean(threadExecutionResults.stream().anyMatch(Future::isDone)));

    timerService.shutdownNow();

    assertThrows(ExecutionException.class, () -> threadExecutionResults.stream()
        .filter(Future::isDone)
        .findAny().get().get());
  }

  private void doStuffWithCache() {
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
}
