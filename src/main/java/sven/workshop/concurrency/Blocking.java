package sven.workshop.concurrency;

public class Blocking extends Thread {

  private final Object lock1;
  private final Object lock2;

  public Blocking(Object lock1, Object lock2) {
    this.lock1 = lock1;
    this.lock2 = lock2;
  }

  public void run() {
    while(true) {
      synchronized(lock1) {
        synchronized(lock2) {
          System.out.print('.');
          System.out.flush();
        }
      }
    }
  }

  public static void main(String[] args) {
    Object lock1 = new Object();
    Object lock2 = new Object();
    Blocking bc1 = new Blocking(lock1, lock2);
    Blocking bc2 = new Blocking(lock2, lock1);
    bc1.start();
    bc2.start();
  }
}
