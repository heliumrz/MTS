package edu.gatech;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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