package vsu.java.labs;

public class Person {
    private static long autoId = 28281;
    private long id;
    private String name;
    private String gender;
    private Department department;
    private long salary;

    public Person(String name, String gender, Department department, long salary) {
        this.id = autoId++;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
    }

    public static long getAutoId() {
        return autoId;
    }

    public static void setAutoId(long autoId) {
        Person.autoId = autoId;
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
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}
