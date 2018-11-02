package sven.workshop.concurrency;

public final class Sleeper {

	private Sleeper() {
		// Hidden default
	}

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Thread derprr derp");
		}
	}

}
