
package org.transport.test.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;
import org.transport.main.Constants;
import org.transport.main.GeneralPerson;
import org.transport.main.ModeOfTransport;
import org.transport.main.MonthlyPass;
import org.transport.main.Pass;
import org.transport.main.PersonType;
import org.transport.main.service.RideService;

public class RideServiceTest {

	@Test
	public void testIfCalculateFareReturnsDiscountedPriceForStudent() {
		RideService rideService = new RideService();
		MonthlyPass monthlyPassMock = Mockito.mock(MonthlyPass.class);
		Mockito.when(monthlyPassMock.getPriceForSubway())
				.thenReturn(new BigDecimal(Constants.monthlyPriceForSubway).setScale(2, RoundingMode.HALF_UP));
		GeneralPerson mockPerson = Mockito.mock(GeneralPerson.class);
		Mockito.when(mockPerson.getPersonType()).thenReturn(PersonType.Student);
		BigDecimal price = rideService.calculateFare(monthlyPassMock, new BigDecimal(0), ModeOfTransport.Subway,
				mockPerson);
		BigDecimal expectedStudentPrice = new BigDecimal(0);
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7) {
			expectedStudentPrice = new BigDecimal(
					Constants.monthlyPriceForSubway * Constants.weekEndDiscountFactor * Constants.studentDiscountFactor)
							.setScale(2, RoundingMode.HALF_UP);
		} else {
			expectedStudentPrice = new BigDecimal(Constants.monthlyPriceForSubway * Constants.studentDiscountFactor)
					.setScale(2, RoundingMode.HALF_UP);
		}

		assertEquals(expectedStudentPrice, price);
	}

	@Test
	public void testIfCalculateFareReturnsDiscountedPriceForRegularPerson() {
		RideService rideService = new RideService();
		MonthlyPass monthlyPassMock = Mockito.mock(MonthlyPass.class);
		Mockito.when(monthlyPassMock.getPriceForSubway())
				.thenReturn(new BigDecimal(Constants.monthlyPriceForSubway).setScale(2, RoundingMode.HALF_UP));
		GeneralPerson mockPerson = Mockito.mock(GeneralPerson.class);
		Mockito.when(mockPerson.getPersonType()).thenReturn(PersonType.Regular);
		BigDecimal price = rideService.calculateFare(monthlyPassMock, new BigDecimal(0), ModeOfTransport.Subway,
				mockPerson);
		BigDecimal expectedPrice = new BigDecimal(0);
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7) {
			expectedPrice = new BigDecimal(Constants.monthlyPriceForSubway * Constants.weekEndDiscountFactor)
					.setScale(2, RoundingMode.HALF_UP);
		} else {
			expectedPrice = new BigDecimal(Constants.monthlyPriceForSubway).setScale(2, RoundingMode.HALF_UP);
		}

		assertEquals(expectedPrice, price);
	}

	@Test
	public void testIfCalculateFareReturnsDiscountedPriceForSenior() {
		RideService rideService = new RideService();
		MonthlyPass monthlyPassMock = Mockito.mock(MonthlyPass.class);
		Mockito.when(monthlyPassMock.getPriceForSubway())
				.thenReturn(new BigDecimal(Constants.monthlyPriceForSubway).setScale(2, RoundingMode.HALF_UP));
		GeneralPerson mockPerson = Mockito.mock(GeneralPerson.class);
		Mockito.when(mockPerson.getPersonType()).thenReturn(PersonType.Senior);
		BigDecimal price = rideService.calculateFare(monthlyPassMock, new BigDecimal(0), ModeOfTransport.Subway,
				mockPerson);
		BigDecimal expectedSeniorCitizenPrice = new BigDecimal(0);
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7) {
			expectedSeniorCitizenPrice = new BigDecimal(
					Constants.monthlyPriceForSubway * Constants.weekEndDiscountFactor * Constants.elderlyDiscountFactor)
							.setScale(2, RoundingMode.HALF_UP);
		} else {
			expectedSeniorCitizenPrice = new BigDecimal(
					Constants.monthlyPriceForSubway * Constants.elderlyDiscountFactor).setScale(2,
							RoundingMode.HALF_UP);
		}

		assertEquals(expectedSeniorCitizenPrice, price);
	}

	@Test
	public void testGeneratePass() {
		RideService rideService = new RideService();
		GeneralPerson personMock = Mockito.mock(GeneralPerson.class);
		ArrayList<Pass> listOfPasses = new ArrayList<Pass>();
		Mockito.when(personMock.getListOfPasses()).thenReturn(listOfPasses);
		Pass p = rideService.generatePass(1, ModeOfTransport.Bus, personMock);
		assertEquals(ModeOfTransport.Bus, p.getModeOfTransport());
		assertEquals(personMock, p.getPerson());
		assertEquals(p.getPassId(), personMock.getListOfPasses().get(0).getPassId());
	}
}
