package hw.streamandoptional.hw28streamandoptional.service;

import hw.streamandoptional.hw28streamandoptional.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, int salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee getLowestPaidEmployee(int department);

    Employee getHighestPaidEmployee(int department);

    List<Employee> printEmployeesForDepartment(int department);

    List<Employee> printEmployeesByDepartments();

    List<Employee> printEmployees();

    List<Employee> fillEmployeesList();

}
