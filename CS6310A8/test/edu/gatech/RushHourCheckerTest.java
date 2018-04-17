package edu.gatech;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class RushHourCheckerTest {

	@Test
	public void test() throws ParseException {
		RushHourChecker rushHourChecker = RushHourChecker.getInstance();
		assertEquals(false, rushHourChecker.isRushHour("07:59"));
		assertEquals(false, rushHourChecker.isRushHour("10:01"));
		assertEquals(false, rushHourChecker.isRushHour("16:59"));
		assertEquals(false, rushHourChecker.isRushHour("19:01"));
		
		assertEquals(true, rushHourChecker.isRushHour("08:01"));
		assertEquals(true, rushHourChecker.isRushHour("09:59"));
		assertEquals(true, rushHourChecker.isRushHour("17:01"));
		assertEquals(true, rushHourChecker.isRushHour("18:59"));
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String date1 = "04-16-2018 07:59:00";
		String date2 = "04-16-2018 10:01:00";
		String date3 = "04-16-2018 07:59:00";
		String date4 = "04-16-2018 07:59:00";
		assertEquals(false, rushHourChecker.isRushHour(dateFormat.parse(date1)));
		assertEquals(false, rushHourChecker.isRushHour(dateFormat.parse(date2)));
		assertEquals(false, rushHourChecker.isRushHour(dateFormat.parse(date3)));
		assertEquals(false, rushHourChecker.isRushHour(dateFormat.parse(date4)));
		
		String date5 = "04-16-2018 08:01:00";
		String date6 = "04-16-2018 09:59:00";
		String date7 = "04-16-2018 17:01:00";
		String date8 = "04-16-2018 18:59:00";
		assertEquals(true, rushHourChecker.isRushHour(dateFormat.parse(date5)));
		assertEquals(true, rushHourChecker.isRushHour(dateFormat.parse(date6)));
		assertEquals(true, rushHourChecker.isRushHour(dateFormat.parse(date7)));
		assertEquals(true, rushHourChecker.isRushHour(dateFormat.parse(date8)));
	}


}
