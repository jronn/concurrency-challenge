package sven.workshop.concurrency;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WhoEndsFirstApplicationTest {
	
	@Test
	public void runTheSleep() {
		
		var list = new WhoEndsFirstSleeping().runMe();
		Assertions.assertEquals(2, list.size());
		Logger.getGlobal().log(Level.INFO, list.toString());
	}

	
	@Test
	public void runTheLoop() {
		
		var list = new WhoEndsFirstLooping().runMe();
		Assertions.assertEquals(0, list.size());
		Logger.getGlobal().log(Level.INFO, list.toString());
	}
	
}
