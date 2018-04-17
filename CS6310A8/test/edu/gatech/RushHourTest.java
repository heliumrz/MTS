package edu.gatech;

import static org.junit.Assert.*;

import org.junit.Test;

public class RushHourTest {

	@Test
	public void test() {
		RushHour rushHour = new RushHour("08:00", "10:00");
		assertEquals(true, rushHour.isRushHour("09:00"));
		assertEquals(false, rushHour.isRushHour("07:59"));
		assertEquals(false, rushHour.isRushHour("10:01"));
	}

}
