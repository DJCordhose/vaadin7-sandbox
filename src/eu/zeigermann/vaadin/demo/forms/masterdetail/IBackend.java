package eu.zeigermann.vaadin.demo.forms.masterdetail;

import java.util.List;

public interface IBackend {
    public List<Person> getPersons();
    public void storePerson(Person person);
    public void deletePerson(Person person);
}