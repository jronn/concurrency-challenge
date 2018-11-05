package sven.workshop.concurrency;

public class ThreadRunner {
	
	public static void main(String[] args) {
		new ThreadRunner().run();
	}

	public void run() {

		Runnable task = () -> System.out.println(Thread.currentThread().getName());

		task.run();
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("Done!");
	}
}
