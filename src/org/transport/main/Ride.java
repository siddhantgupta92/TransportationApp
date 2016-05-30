package org.transport.main;
import java.util.Date;

public class Ride {
	private Date date;
	private Person person;
	private Pass pass;
	private ModeOfTransport modeOfTransport;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Pass getPass() {
		return pass;
	}

	public void setPass(Pass pass) {
		this.pass = pass;
	}

	public ModeOfTransport getModeOfTransport(int modeChoice) {
		for (ModeOfTransport m : ModeOfTransport.values()) {
			if (m.getModeChoice() == modeChoice)
				return m;
		}
		return null;
	}

	public void setModeOfTransport(ModeOfTransport modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

}
