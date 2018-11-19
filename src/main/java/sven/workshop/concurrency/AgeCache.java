package sven.workshop.concurrency;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class AgeCache {

  private final Map<String, Data> cacheMap = new HashMap<>();

  Optional<Integer> getAge(final String key) {
    return Optional.ofNullable(cacheMap.get(key)).map(Data::getAge);
  }

  void setAge(final String key, final Integer age) {
    
    if (!cacheMap.containsKey(key)) {
      cacheMap.put(key, new Data(age));
    }
  }
  
  Optional<Instant> getCreationTime(String key) {
    return Optional.ofNullable(cacheMap.get(key)).map(Data::getCreationTime); 
  }

  void reset(final String key) {
    cacheMap.remove(key);
  }
  
  int size() {
    return cacheMap.size();
  }

  private final class Data {

    private final Instant creationTime = Instant.now();
    private final Integer age;

    Data(final Integer age) {
      this.age = age;
    }

    public Instant getCreationTime() {
      return creationTime;
    }

    public Integer getAge() {
      return age;
    }

  }

}
