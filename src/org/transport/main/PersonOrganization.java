package org.transport.main;

import java.util.ArrayList;

public class PersonOrganization {
	private ArrayList<Person> personList = new ArrayList<Person>();

	public PersonOrganization() {
		this.setPersonList();
	}

	public ArrayList<Person> getPersonList() {
		return personList;
	}

	private void setPersonList() {
		GeneralPerson p1 = new GeneralPerson();
		p1.setName("Amber");
		p1.setPersonId(1);
		p1.setPersonType(PersonType.Student);
		personList.add(p1);
		GeneralPerson p2 = new GeneralPerson();
		p2.setName("Rose");
		p2.setPersonId(2);
		p2.setPersonType(PersonType.Student);
		personList.add(p2);
		TransportationWorker t1 = new TransportationWorker();
		t1.setName("Ashley");
		t1.setPersonId(3);
		t1.setPersonType(PersonType.Worker);
		personList.add(t1);
		GeneralPerson s1 = new GeneralPerson();
		s1.setName("Roger");
		s1.setPersonId(4);
		s1.setPersonType(PersonType.Senior);
		personList.add(s1);
		GeneralPerson r1 = new GeneralPerson();
		r1.setName("Roger");
		r1.setPersonId(5);
		r1.setPersonType(PersonType.Regular);
		personList.add(r1);
	}

	public Person getPerson(long id) {
		for (Person person : personList) {
			if ((id) == (person.getPersonId())) {
				return person;
			}
		}
		return null;
	}
}
