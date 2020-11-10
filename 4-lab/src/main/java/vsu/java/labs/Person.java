package vsu.java.labs;

import java.util.Date;
import java.util.Objects;

/**
 * Person class
 */
public class Person {
    private static long autoId;
    private long id;
    private String name;
    private String gender;
    private Date birthDate;
    private Department department;
    private long salary;

    /**
     * Creates a person by name, gender, birthDate, department, salary
     *
     * @param name - name of person
     * @param gender - gender of person
     * @param birthDate - birthday of person
     * @param department - department of person
     * @param salary - salary of person
     */
    public Person(String name, String gender, Date birthDate, Department department, long salary) {
        this.id = autoId++;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.department = department;
        this.salary = salary;
    }

    /**
     * Creates a person by id, name, gender, birthDate, department, salary
     *
     * @param id - id of person
     * @param name - name of person
     * @param gender - gender of person
     * @param birthDate - birthday of person
     * @param department - department of person
     * @param salary - salary of person
     */
    public Person(long id, String name, String gender, Date birthDate, Department department, long salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.department = department;
        this.salary = salary;

        autoId = Math.max(id + 1, autoId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                salary == person.salary &&
                Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(department, person.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, birthDate, department, salary);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}
