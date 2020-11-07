package vsu.java.labs;

import java.util.HashSet;
import java.util.Set;

public class PersonFactory {
    private Set<Person> personSet = new HashSet<>();

    public Person getPerson(String name, String gender, Department department, long salary) {
        for (Person person : personSet) {
            if (person.getName().equals(name) &&
                person.getGender().equals(gender) &&
                person.getDepartment().equals(department) &&
                person.getSalary() == salary) {
                return person;
            }
        }
        Person person = new Person(name, gender, department, salary);
        personSet.add(person);
        return person;
    }
}
