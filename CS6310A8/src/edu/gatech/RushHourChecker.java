package edu.gatech;

import java.util.Date;

public class RushHourChecker {
	static final String MORNING_RUSH_HOUR_START = "08:00";
    static final String MORNING_RUSH_HOUR_END = "10:00";
	static final String AFTERNOON_RUSH_HOUR_START = "17:00";
	static final String AFTERNOON_RUSH_HOUR_END = "19:00";

	RushHour morningRush = new RushHour(MORNING_RUSH_HOUR_START, MORNING_RUSH_HOUR_END);
	RushHour afternoonRush = new RushHour(AFTERNOON_RUSH_HOUR_START, AFTERNOON_RUSH_HOUR_END);

	private static RushHourChecker instance = null;

	public static RushHourChecker getInstance() {
		if (instance == null) {
			instance = new RushHourChecker();
		}
		return instance;
	}

	// string format "08:00"
	public boolean isRushHour(String dateStr) {
		return morningRush.isRushHour(dateStr) || afternoonRush.isRushHour(dateStr);
	}
	
	public boolean isRushHour(Date date) {
		return morningRush.isRushHour(date) || afternoonRush.isRushHour(date);
	}
}
