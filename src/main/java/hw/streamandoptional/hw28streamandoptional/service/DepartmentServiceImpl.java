package hw.streamandoptional.hw28streamandoptional.service;

import hw.streamandoptional.hw28streamandoptional.model.Employee;
import org.apache.catalina.authenticator.Constants;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Employee getLowestPaidEmployee(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    }

    @Override
    public Employee getHighestPaidEmployee(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    }

    @Override
    public List<Employee> printEmployeesForDepartment(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> printEmployeesByDepartments() {
        return employeeService.getEmployees().stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment)).
                collect(Collectors.toUnmodifiableList());
    }
}
