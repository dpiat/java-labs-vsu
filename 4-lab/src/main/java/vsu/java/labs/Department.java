package vsu.java.labs;

public class Department {
    private static long id;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        Department.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
