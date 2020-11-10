package vsu.java.labs;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Person class
 */
public class PersonFactory {
    private Set<Person> personSet = new HashSet<>();

    /**
     * This method returns a new Person if it not contained in personSet
     *
     * @param id - id of person
     * @param name - name of person
     * @param gender - gender of person
     * @param birthDate - birthday of person
     * @param department - department of person
     * @param salary - salary of person
     * @return
     */
    public Person getPerson(long id, String name, String gender, Date birthDate, Department department, long salary) {
        for (Person person : personSet) {
            if (person.getId() == id) {
                return person;
            }
        }
        Person person = new Person(id, name, gender, birthDate, department, salary);
        personSet.add(person);
        return person;
    }
}
