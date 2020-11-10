package vsu.java.labs;

import java.util.HashSet;
import java.util.Set;

/**
 * DepartmentFactory class.
 */
public class DepartmentFactory {
    private Set<Department> departmentSet = new HashSet<>();

    /**
     * This method returns a new department if it not contained in departmentSet
     *
     * @param name - name of department
     * @return department
     */
    public Department getDepartment(String name) {
        for (Department department : departmentSet) {
            if (department.getName().equals(name)) {
                return department;
            }
        }
        Department department = new Department(name);
        departmentSet.add(department);
        return department;
    }
}
