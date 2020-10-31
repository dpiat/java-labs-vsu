package vsu.java.labs;

import java.util.HashSet;
import java.util.Set;

public class PersonFactory {
    private Set<Person> personSet = new HashSet<>();

    public Person getPerson(String name, String gender, Department department, long salary) {
        for (Person person : personSet) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        Person person = new Person();
        personSet.add(person);
        return person;
    }
}
