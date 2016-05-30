package org.transport.test.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.Mockito;
import org.transport.main.PrepaidPass;
import org.transport.main.service.PrepaidPassService;

public class PrepaidPassServiceTest {

	@Test
	public void testCheckBalanceReturnsTrueWhenFareLessThanBalance() {
		BigDecimal fareLessThanBalance = new BigDecimal(10);
		PrepaidPass pass = Mockito.mock(PrepaidPass.class);
		Mockito.when(pass.getBalance()).thenReturn(new BigDecimal(12));
		PrepaidPassService prepaidPassService = new PrepaidPassService();
		boolean resultForFareLessThanBalance = prepaidPassService.checkBalance(fareLessThanBalance, pass);
		assertTrue(resultForFareLessThanBalance);

	}

	@Test
	public void testCheckBalanceReturnsTrueWhenFareEqualToBalance() {
		BigDecimal fareEqualToBalance = new BigDecimal(12);
		PrepaidPass pass = Mockito.mock(PrepaidPass.class);
		Mockito.when(pass.getBalance()).thenReturn(new BigDecimal(12));
		PrepaidPassService prepaidPassService = new PrepaidPassService();
		boolean resultForFareEqualToBalance = prepaidPassService.checkBalance(fareEqualToBalance, pass);
		assertTrue(resultForFareEqualToBalance);

	}

	@Test
	public void testCheckBalanceReturnsFalseWhenFareMoreThanBalance() {
		BigDecimal fareGreaterThanBalance = new BigDecimal(13);
		PrepaidPass pass = Mockito.mock(PrepaidPass.class);
		Mockito.when(pass.getBalance()).thenReturn(new BigDecimal(12));
		PrepaidPassService prepaidPassService = new PrepaidPassService();
		boolean resultForFareMoreThanBalance = prepaidPassService.checkBalance(fareGreaterThanBalance, pass);
		assertFalse(resultForFareMoreThanBalance);

	}

	@Test
	public void testIfCheckBalanceSetsCorrectBalance() {
		BigDecimal fare = new BigDecimal(9);
		PrepaidPass pass = Mockito.mock(PrepaidPass.class);
		Mockito.when(pass.getBalance()).thenReturn(new BigDecimal(12));
		PrepaidPassService prepaidPassService = new PrepaidPassService();
		prepaidPassService.checkBalance(fare, pass);
		Mockito.verify(pass, Mockito.times(1)).setBalance(new BigDecimal(3));

	}

}
