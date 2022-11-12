package hw.streamandoptional.hw28streamandoptional.service;

import hw.streamandoptional.hw28streamandoptional.exception.InvalidInputException;
import hw.streamandoptional.hw28streamandoptional.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees;

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }



    @Override
    public Employee addEmployee(String firstName, String lastName, Integer department, Integer salary) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            throw new RuntimeException("Сотрудник уже имеется в массиве");
        }
        employees.add(employee);
        return employee;
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        Employee employee = findEmployee(firstName, lastName);
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        final Optional<Employee> employee = employees.stream()
                .filter(e -> e.getFirstName().equals(firstName)
                        && e.getLastName().equals(lastName))
                .findAny();
        return employee.orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    }

    @Override
    public Employee getLowestPaidEmployee(Integer department) {
        return employees.stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    }

    @Override
    public Employee getHighestPaidEmployee(Integer department) {
        return employees.stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    }

    @Override
    public List<Employee> printEmployeesForDepartment(Integer department) {
        return employees.stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> printEmployeesByDepartments() {
        return employees.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Employee> printEmployees() {
        return Collections.unmodifiableList(employees);
    }

    @Override
    public List<Employee> fillEmployeesList() {
        employees.add(new Employee("Michael", "Jackson", 80_000, 2));
        employees.add(new Employee("Bon", "Jovi", 120_000, 1));
        employees.add(new Employee("Jared", "Leto", 55_000, 3));
        employees.add(new Employee("Mike", "Shinoda", 65_000, 4));
        employees.add(new Employee("Chad", "Crueger", 75_000, 5));
        employees.add(new Employee("Steve", "Taylor", 30_000, 1));
        employees.add(new Employee("Chester", "Bennington", 130_000, 3));
        return employees;
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}

