package org.transport.main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

public class PrepaidPass implements Pass {

	private Person person;
	private ModeOfTransport modeOfTransport;
	private BigDecimal balance;
	private Long passId;
	private BigDecimal price = new BigDecimal(0, new MathContext(2));
	private BigDecimal rideFareForSubway = new BigDecimal(Constants.singleRideFareForSubway, new MathContext(2));
	private BigDecimal rideFareForBus = new BigDecimal(Constants.singleRideFareForBus, new MathContext(2));
	private BigDecimal rideFareForCommuter = new BigDecimal(Constants.singleRideFareForCommuter, new MathContext(2));

	public BigDecimal getRideFare() {
		return rideFareForSubway;
	}

	public void setRideFare(BigDecimal rideFare) {
		this.rideFareForSubway = rideFare;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public PrepaidPass() {
		balance = new BigDecimal(0);
		passId = new Random().nextLong();
	}

	public long getPassId() {
		return passId;
	}

	public void setPassId(Long passId) {
		this.passId = passId;
	}

	public void setModeOfTransport(ModeOfTransport modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setPerson(Person person) {
		this.person = person;
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

	public void usePass() {

	}

	public BigDecimal getRideFareForBus() {
		return rideFareForBus;
	}

	public void setRideFareForBus(BigDecimal rideFareForBus) {
		this.rideFareForBus = rideFareForBus;
	}

	public BigDecimal getRideFareForCommuter() {
		return rideFareForCommuter;
	}

	public void setRideFareForCommuter(BigDecimal rideFareForCommuter) {
		this.rideFareForCommuter = rideFareForCommuter;
	}

}
