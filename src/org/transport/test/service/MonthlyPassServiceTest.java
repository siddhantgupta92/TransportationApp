package org.transport.test.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;
import org.mockito.Mockito;
import org.transport.main.Constants;
import org.transport.main.MonthlyPass;
import org.transport.main.service.MonthlyPassService;

public class MonthlyPassServiceTest {

	@Test
	public void testIfCheckValidityCallsGetValidUntil() {

		MonthlyPassService monthlyPassService = new MonthlyPassService();
		MonthlyPass mockMonthlyPass = Mockito.mock(MonthlyPass.class);
		monthlyPassService.checkValidity(mockMonthlyPass);
		Mockito.verify(mockMonthlyPass, Mockito.times(1)).getValidUntil();
	}

	@Test
	public void testIfCheckValidityReturnsTrue() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(Constants.timeZone));
		cal.add(Calendar.MONTH, 1);
		long validUntil = cal.getTimeInMillis();
		MonthlyPassService monthlyPassService = new MonthlyPassService();
		MonthlyPass mockMonthlyPass = Mockito.mock(MonthlyPass.class);
		Mockito.when(mockMonthlyPass.getValidUntil()).thenReturn(validUntil);
		boolean returnValue = monthlyPassService.checkValidity(mockMonthlyPass);
		Mockito.verify(mockMonthlyPass, Mockito.times(1)).getValidUntil();
		assertTrue(returnValue);
	}

	@Test
	public void testIfCheckValidityReturnsFalse() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(Constants.timeZone));
		cal.add(Calendar.MONTH, -1);
		long validUntil = cal.getTimeInMillis();
		MonthlyPassService monthlyPassService = new MonthlyPassService();
		MonthlyPass mockMonthlyPass = Mockito.mock(MonthlyPass.class);
		Mockito.when(mockMonthlyPass.getValidUntil()).thenReturn(validUntil);
		boolean returnValue = monthlyPassService.checkValidity(mockMonthlyPass);
		Mockito.verify(mockMonthlyPass, Mockito.times(1)).getValidUntil();
		assertFalse(returnValue);
	}
}
