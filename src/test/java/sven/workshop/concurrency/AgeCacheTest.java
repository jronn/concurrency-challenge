package sven.workshop.concurrency;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AgeCacheTest {

  @Test
  public void breakit() {
    
    var cache = new AgeCache();
    
    IntStream.range(0, 1000)
        .parallel()
        .forEach(i -> cache.setAge(String.valueOf(i), i));
    
    Assertions.assertEquals(1000, cache.size());
    
  }
  
}
