package org.transport.main.service;

import java.util.ArrayList;

import org.transport.main.GeneralPerson;
import org.transport.main.ModeOfTransport;
import org.transport.main.MonthlyPass;
import org.transport.main.Pass;
import org.transport.main.PrepaidPass;
import org.transport.main.Ride;

public class GeneralPersonService {
	MonthlyPassService monthlyPassService = new MonthlyPassService();

	public Pass getValidPrepaidCard(long cardId, GeneralPerson person) {
		ArrayList<Pass> listOfPasses = person.getListOfPasses();
		for (Pass p : listOfPasses) {
			if (p.getPassId() == cardId && p instanceof PrepaidPass) {
				return p;
			}
		}
		return null;
	}

	public Pass usePass(long passId, Ride r, ModeOfTransport modeOfTransport, GeneralPerson person) {
		ArrayList<Pass> listOfPasses = person.getListOfPasses();
		Pass pass = null;
		for (Pass p : listOfPasses) {
			if (p.getPassId() == passId && p.getModeOfTransport() == modeOfTransport) {
				pass = p;
			}
		}
		if (pass instanceof MonthlyPass) {

			if (monthlyPassService.checkValidity((MonthlyPass) pass)) {
				return pass;
			}

		} else if (pass instanceof PrepaidPass) {
			return pass;
		}

		return null;
	}

	public void setMonthlyPassService(MonthlyPassService monthlyPassService) {
		this.monthlyPassService = monthlyPassService;
	}
}
