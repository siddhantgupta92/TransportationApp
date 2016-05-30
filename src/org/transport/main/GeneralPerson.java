package org.transport.main;

import java.util.ArrayList;

public class GeneralPerson implements Person {
	private String name;
	private ArrayList<Pass> listOfPasses = new ArrayList<Pass>();
	private long personId;
	private PersonType personType;

	public ArrayList<Pass> getListOfPasses() {
		return listOfPasses;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public void setListOfPasses(ArrayList<Pass> listOfPasses) {
		this.listOfPasses = listOfPasses;
	}

	public String getName() {
		return name;
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
