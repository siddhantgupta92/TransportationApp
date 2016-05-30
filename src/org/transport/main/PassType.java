package org.transport.main;


public enum PassType {
	PREPAID(1), MONTHLY(2);

	private int passChoice;

	private PassType(int passChoice) {
		this.passChoice = passChoice;
	}

	public int getPassChoice() {
		return this.passChoice;
	}

}
