package sven.workshop.concurrency;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WhoEndsFirstApplicationTest {
	
	@Test
	public void runTheSleep() {
		
		var list = new ThreadSleeping().runMe();
		Assertions.assertEquals(2, list.size());
		Logger.getGlobal().log(Level.INFO, list.toString());
	}

	
	@Test
	public void runTheLoop() {
		
		var list = new ThreadLooping().runMe();
		Assertions.assertEquals(3, list.size());
		Logger.getGlobal().log(Level.INFO, list.toString());
	}
	
}
