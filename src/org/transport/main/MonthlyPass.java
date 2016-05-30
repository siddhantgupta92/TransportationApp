package org.transport.main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public class MonthlyPass implements Pass {
	private Long passId;
	private GeneralPerson person;
	private ModeOfTransport modeOfTransport;
	private Long validUntil;
	private BigDecimal priceForSubway = new BigDecimal(Constants.monthlyPriceForSubway, new MathContext(2));
	private BigDecimal priceForBus = new BigDecimal(Constants.monthlyPriceForBus, new MathContext(2));
	private BigDecimal priceForCommuter = new BigDecimal(Constants.monthlyPriceForCommuter, new MathContext(2));

	public MonthlyPass() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(Constants.timeZone));
		cal.add(Calendar.MONTH, 1);
		this.validUntil = cal.getTimeInMillis();
		this.passId = new Random().nextLong();
	}

	public long getPassId() {
		return passId;
	}

	public void setPassId(Long passId) {
		this.passId = passId;
	}

	public void setValidUntil(Long validUntil) {
		this.validUntil = validUntil;
	}

	@Override
	public Person getPerson() {
		// TODO Auto-generated method stub
		return person;
	}

	public ModeOfTransport getModeOfTransport() {
		// TODO Auto-generated method stub
		return modeOfTransport;
	}

	public void setModeOfTransport(ModeOfTransport modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public Long getValidUntil() {
		return validUntil;
	}

	public void setPerson(GeneralPerson person) {
		this.person = person;
	}

	@Override
	public void usePass() {

	}

	public BigDecimal getPriceForSubway() {
		return priceForSubway;
	}

	public void setPriceForSubway(BigDecimal price) {
		this.priceForSubway = price;
	}

	public BigDecimal getPriceForBus() {
		return priceForBus;
	}

	public void setPriceForBus(BigDecimal priceForBus) {
		this.priceForBus = priceForBus;
	}

	public BigDecimal getPriceForCommuter() {
		return priceForCommuter;
	}

	public void setPriceForCommuter(BigDecimal priceForCommuter) {
		this.priceForCommuter = priceForCommuter;
	}

}
