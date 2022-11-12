package hw.streamandoptional.hw28streamandoptional.service;

import hw.streamandoptional.hw28streamandoptional.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, Integer department, Integer salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee getLowestPaidEmployee(Integer department);

    Employee getHighestPaidEmployee(Integer department);

    List<Employee> printEmployeesForDepartment(Integer department);

    List<Employee> printEmployeesByDepartments();

    List<Employee> printEmployees();

    List<Employee> fillEmployeesList();


    List<Employee> getEmployees();
}
