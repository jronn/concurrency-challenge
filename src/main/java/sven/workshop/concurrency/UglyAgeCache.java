package sven.workshop.concurrency;

public final class UglyAgeCache implements AgeCache {

  private volatile int age;
  private final Object lock = new Object();

  UglyAgeCache(final int startAge) {
    age = startAge;
  }

  @Override
  public int getAge() {
    synchronized (lock) {
      return age;
    }
  }

  @Override
  public void increase(final int amount) {
    synchronized (lock) {
      final int check = age + amount;
      age = check;
      if (age != check) {
        throw new AssertionError("Data Race Detected");
      }
    }
  }

  @Override
  public void decrease(final int amount) {
    increase(-amount);
  }

}
