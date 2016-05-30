package org.transport.main.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;

import org.transport.main.Constants;
import org.transport.main.GeneralPerson;
import org.transport.main.ModeOfTransport;
import org.transport.main.MonthlyPass;
import org.transport.main.Pass;
import org.transport.main.PassType;
import org.transport.main.Person;
import org.transport.main.PersonType;
import org.transport.main.PrepaidPass;

public class RideService {
	public BigDecimal calculateFare(Pass pass, BigDecimal price, ModeOfTransport modeOfTransport, Person person) {
		if (pass instanceof MonthlyPass) {
			if (modeOfTransport == ModeOfTransport.Subway)
				price = ((MonthlyPass) pass).getPriceForSubway();
			else if (modeOfTransport == ModeOfTransport.Commuter)
				price = ((MonthlyPass) pass).getPriceForCommuter();
			else if (modeOfTransport == ModeOfTransport.Bus)
				price = ((MonthlyPass) pass).getPriceForBus();

		} else if (pass instanceof PrepaidPass) {
			((PrepaidPass) pass).setBalance(price);
		}
		double weekendDiscount = Constants.weekEndDiscountFactor;
		double studentDiscount = Constants.studentDiscountFactor;
		double elderlyDiscount = Constants.elderlyDiscountFactor;
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7) {
			price = price.multiply(new BigDecimal(weekendDiscount)).setScale(2, RoundingMode.HALF_UP);

		}
		if (person instanceof GeneralPerson) {
			if (((GeneralPerson) person).getPersonType() == (PersonType.Student)) {
				price = price.multiply(new BigDecimal(studentDiscount)).setScale(2, RoundingMode.HALF_UP);
			} else if (((GeneralPerson) person).getPersonType() == (PersonType.Senior)) {
				price = price.multiply(new BigDecimal(elderlyDiscount)).setScale(2, RoundingMode.HALF_UP);
			}
		}
		return price;
	}

	public BigDecimal calculateFare(Pass pass, ModeOfTransport modeOfTransport, Person person) {
		BigDecimal price = null;
		if (modeOfTransport == ModeOfTransport.Subway)
			price = ((PrepaidPass) pass).getRideFare();
		else if (modeOfTransport == ModeOfTransport.Bus)
			price = ((PrepaidPass) pass).getRideFareForBus();
		else if (modeOfTransport == ModeOfTransport.Commuter)
			price = ((PrepaidPass) pass).getRideFareForCommuter();
		double weekendDiscount = Constants.weekEndDiscountFactor;
		double studentDiscount = Constants.studentDiscountFactor;
		double elderlyDiscount = Constants.elderlyDiscountFactor;
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7) {
			price = price.multiply(new BigDecimal(weekendDiscount)).setScale(2, RoundingMode.HALF_UP);
		}
		if (((GeneralPerson) person).getPersonType().equals(PersonType.Student)) {
			price = price.multiply(new BigDecimal(studentDiscount)).setScale(2, RoundingMode.HALF_UP);
		} else if (((GeneralPerson) person).getPersonType().equals(PersonType.Senior)) {
			price = price.multiply(new BigDecimal(elderlyDiscount)).setScale(2, RoundingMode.HALF_UP);
		}

		return price;
	}

	public Pass generatePass(int passChoice, ModeOfTransport modeOfTransport, Person person) {
		ArrayList<Pass> listOfPasses = ((GeneralPerson) person).getListOfPasses();
		if (passChoice == ((PassType.PREPAID).getPassChoice())) {
			PrepaidPass p = new PrepaidPass();
			p.setModeOfTransport(modeOfTransport);
			p.setPerson(person);
			listOfPasses.add(p);
			return p;
		} else if (passChoice == (PassType.MONTHLY).getPassChoice()) {
			MonthlyPass m = new MonthlyPass();
			m.setModeOfTransport(modeOfTransport);
			m.setPerson((GeneralPerson) person);
			listOfPasses.add(m);
			return m;
		}
		return null;
	}
}
