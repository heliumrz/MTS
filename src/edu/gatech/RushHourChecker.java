package edu.gatech;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RushHourChecker{
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
	
	class RushHour {
		// Note: String format must be: (08:00)
		private Date start;
		private Date end;

		RushHour(String startStr, String endStr) {
			setStart(startStr);
			setEnd(endStr);
		}
		
		public Date getStart() {
			return start;
		}
		
		public void setStart(Date start) {
			this.start = start;
		}

		// Note format: 08:00
		public void setStart(String startStr) {
			this.start = parseDate(startStr);
		}
		
		public Date getEnd() {
			return end;
		}

		public void setEnd(Date end) {
			this.end = end;
		}

		// Note format: 10:00
		public void setEnd(String endStr) {
			this.end = parseDate(endStr);
		}
		
		public Date parseDate(String date) {
			final String inputFormat = "HH:mm";
			SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
			try {
				return inputParser.parse(date);
			} catch (java.text.ParseException e) {
				return new Date(0);
			}
		}

		// Note format: 09:00
		public boolean isRushHour(String dateStr) {
			Date date = parseDate(dateStr);
			return start.before(date) && end.after(date);
		}
		
		public boolean isRushHour(Date date) {
			final String inputFormat = "HH:mm";
			SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
			String dateStr = inputParser.format(date);
			return isRushHour(dateStr);
		}
	}
}


