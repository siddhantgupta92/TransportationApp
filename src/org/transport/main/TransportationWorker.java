package org.transport.main;

import java.util.Random;

public class TransportationWorker implements Person {
	String name;
	TransportationWorkerPass tWorkerPass;
	long personId;
	private PersonType personType;

	public TransportationWorker() {
		tWorkerPass = new TransportationWorkerPass();
		tWorkerPass.setPassId((new Random().nextLong()));
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public TransportationWorkerPass gettWorkerPass() {
		return tWorkerPass;
	}

	public void settWorkerPass(TransportationWorkerPass tWorkerPass) {
		this.tWorkerPass = tWorkerPass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

}
