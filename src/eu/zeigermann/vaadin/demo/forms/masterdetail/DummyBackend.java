package eu.zeigermann.vaadin.demo.forms.masterdetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class DummyBackend implements IBackend, Serializable {
	private final List<Person> persons = new ArrayList<Person>();

	@Override
	public List<Person> getPersons() {
		return persons;
	}

	@Override
	public void storePerson(Person person) {
		persons.add(person);
	}

	@Override
	public void deletePerson(Person person) {
		persons.remove(person);
	}
}