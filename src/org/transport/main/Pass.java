package org.transport.main;

public interface Pass {
	Person getPerson();

	void usePass();

	long getPassId();

	void setPassId(Long passId);

	ModeOfTransport getModeOfTransport();
}
