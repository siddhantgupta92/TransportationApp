package org.transport.main;

public enum ModeOfTransport {
	Commuter(1), Bus(2), Subway(3);

	private int mode;

	private ModeOfTransport(int mode) {
		this.mode = mode;
	}

	public int getModeChoice() {
		return mode;
	}

}
