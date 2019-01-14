package sven.workshop.concurrency;

public final class DangerousAgeCache {

  private volatile int age;

  public DangerousAgeCache(final int startAge) {
    age = startAge;
  }

  int getAge() {
    return age;
  }

  public void increase(final int amount) {

    final int check = age + amount;
    age = check;
    if (age != check) {
      throw new AssertionError("Data Race Detected");
    }
  }

  public void decrease(final int amount) {
    increase(-amount);
  }

}
