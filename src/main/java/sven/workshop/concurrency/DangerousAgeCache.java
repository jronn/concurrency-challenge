package sven.workshop.concurrency;

public final class DangerousAgeCache implements AgeCache {

  private volatile int age;

  DangerousAgeCache(final int startAge) {
    age = startAge;
  }

  @Override
  public int getAge() {
    return age;
  }

  @Override
  public void increase(final int amount) {
    final int check = age + amount;
    age = check;
    if (age != check) {
      throw new AssertionError("Data Race Detected");
    }
  }

  @Override
  public void decrease(final int amount) {
    increase(-amount);
  }

}
