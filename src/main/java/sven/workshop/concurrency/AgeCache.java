package sven.workshop.concurrency;



public interface AgeCache {

  int getAge();

  void increase(int amount);

  void decrease(int amount);

}
