package org.transport.main;
public class TransportationWorkerPass implements Pass {
	private Person person;
	private long passId;
	private ModeOfTransport modeOfTransport;

	public long getPassId() {
		return passId;
	}

	public void setPassId(Long passId) {
		this.passId = passId;
	}

	private boolean isValid;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
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
		return modeOfTransport;
	}

	public void setModeOfTransport(ModeOfTransport modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	@Override
	public void usePass() {
		// TODO Auto-generated method stub

	}

}
