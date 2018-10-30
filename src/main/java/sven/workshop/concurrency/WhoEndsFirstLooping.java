package sven.workshop.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WhoEndsFirstLooping {

	private List<String> threadReady = new ArrayList<>();

	Runnable worker = () -> {
		Logger.getGlobal().log(Level.INFO, Thread.currentThread().getName() + " started");
		makeFakeWork();
	};

	private synchronized void makeFakeWork() {

		while (!Thread.interrupted()) {
			// Do some Fakey McFake Work
		}
		threadReady.add(Thread.currentThread().getName());
	}

	public List<String> runMe() {

		var t0 = new Thread(worker);
		var t1 = new Thread(worker);
		var t2 = new Thread(worker);

		t0.start();
		t1.start();
		t1.interrupt();
		t2.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		t0.interrupt();
		t2.interrupt();
		return threadReady;
	}

}
