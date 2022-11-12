package hw.streamandoptional.hw28streamandoptional.service;

import hw.streamandoptional.hw28streamandoptional.model.Employee;

import java.util.List;

public interface DepartmentService {
    Employee getLowestPaidEmployee(Integer department);

    Employee getHighestPaidEmployee(Integer department);

    List<Employee> printEmployeesForDepartment(Integer department);

    List<Employee> printEmployeesByDepartments();
}
