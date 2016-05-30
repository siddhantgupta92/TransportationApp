package org.transport.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;
import org.transport.main.GeneralPerson;
import org.transport.main.ModeOfTransport;
import org.transport.main.MonthlyPass;
import org.transport.main.Pass;
import org.transport.main.PrepaidPass;
import org.transport.main.Ride;
import org.transport.main.service.GeneralPersonService;
import org.transport.main.service.MonthlyPassService;

public class GeneralPesronServiceTest {

	@Test
	public void testIfCheckValidCardReturnsCard() {
		GeneralPerson generalPersonMock = Mockito.mock(GeneralPerson.class);
		ArrayList<Pass> listOfPasses = new ArrayList<Pass>();
		Pass p = new PrepaidPass();
		p.setPassId(1l);
		listOfPasses.add(p);
		Mockito.when(generalPersonMock.getListOfPasses()).thenReturn(listOfPasses);
		GeneralPersonService generalPersonService = new GeneralPersonService();
		Pass returnedPass = generalPersonService.getValidPrepaidCard(1, generalPersonMock);
		assertEquals(1, returnedPass.getPassId());
	}

	@Test
	public void testIfUsePassReturnsPass() {
		GeneralPerson generalPersonMock = Mockito.mock(GeneralPerson.class);
		ArrayList<Pass> listOfPasses = new ArrayList<Pass>();
		PrepaidPass p = Mockito.mock(PrepaidPass.class);
		Mockito.when(p.getPassId()).thenReturn(1l);
		Mockito.when(p.getModeOfTransport()).thenReturn(ModeOfTransport.Bus);
		listOfPasses.add(p);
		Mockito.when(generalPersonMock.getListOfPasses()).thenReturn(listOfPasses);
		GeneralPersonService generalPersonService = new GeneralPersonService();
		Pass returnedPass = generalPersonService.usePass(1l, new Ride(), ModeOfTransport.Bus, generalPersonMock);
		assertNotNull(returnedPass);

	}

	@Test
	public void testIfUsePassReturnsMonthlyPass() {
		GeneralPerson generalPersonMock = Mockito.mock(GeneralPerson.class);
		MonthlyPass p = Mockito.mock(MonthlyPass.class);
		Mockito.when(p.getPassId()).thenReturn(1l);
		Mockito.when(p.getModeOfTransport()).thenReturn(ModeOfTransport.Bus);
		ArrayList<Pass> listOfPasses = new ArrayList<Pass>();
		listOfPasses.add(p);
		Mockito.when(generalPersonMock.getListOfPasses()).thenReturn(listOfPasses);
		MonthlyPassService monthlyPassServiceMock = Mockito.mock(MonthlyPassService.class);
		Mockito.when(monthlyPassServiceMock.checkValidity(Mockito.any())).thenReturn(true);
		GeneralPersonService generalPersonService = new GeneralPersonService();
		generalPersonService.setMonthlyPassService(monthlyPassServiceMock);
		Pass returnedPass = generalPersonService.usePass(1l, new Ride(), ModeOfTransport.Bus, generalPersonMock);
		assertNotNull(returnedPass);
	}
}
