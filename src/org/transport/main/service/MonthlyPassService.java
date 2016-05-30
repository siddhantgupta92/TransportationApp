package org.transport.main.service;

import java.util.Calendar;
import java.util.TimeZone;

import org.transport.main.Constants;
import org.transport.main.MonthlyPass;

public class MonthlyPassService {
	public boolean checkValidity(MonthlyPass pass) {
		if (getCurrentTime() < pass.getValidUntil())
			return true;
		return false;
	}

	private long getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(Constants.timeZone));
		return cal.getTimeInMillis();
	}
}
