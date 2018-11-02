package sven.workshop.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSleeping {
	
	private List<String> threadReady = new CopyOnWriteArrayList<>();
	
	Runnable worker = () -> {
		Logger.getGlobal().log(Level.INFO, Thread.currentThread().getName() + " started");
		makeFakeWork();
	};
	
	private synchronized void makeFakeWork() {
		try {
			Sleeper.sleep(10);
			threadReady.add(Thread.currentThread().getName());
		} catch (RuntimeException e) {
			Logger.getGlobal().log(Level.SEVERE, Thread.currentThread().getName() + " interrupted");
		}
	}
	
	public List<String> runMe() {
		
		var t0 = new Thread(worker);
		var t1 = new Thread(worker);
		var t2 = new Thread(worker);
		
		t0.start();
		t1.start();
		t2.start();
		t1.interrupt();
		Sleeper.sleep(100);
		
		return threadReady;
	}
}
