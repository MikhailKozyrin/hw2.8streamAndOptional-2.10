package hw.streamandoptional.hw28streamandoptional.controller;

import hw.streamandoptional.hw28streamandoptional.model.Employee;
import hw.streamandoptional.hw28streamandoptional.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public Object addEmployee(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "salary") Integer salary,
            @RequestParam(value = "department") Integer department) {
        Employee employee = null;
        try {
            employee = employeeService.addEmployee(firstName, lastName, salary, department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }
    @GetMapping(path = "/remove")
    public Object removeEmployee(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        Employee employee = null;
        try {
            employee = employeeService.removeEmployee(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }
    @GetMapping(path = "/find")
    public Object findEmployee(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        Employee employee = null;
        try {
            employee = employeeService.findEmployee(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }
    @GetMapping(path = "/departments/max-salary")
    public Object getHighestPaidEmployee(
            @RequestParam(value = "departmentId") Integer department) {
        Employee employee = null;
        try {
            employee = employeeService.getHighestPaidEmployee(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }
    @GetMapping(path = "/departments/min-salary")
    public Object getLowestPaidEmployee(
            @RequestParam(value = "departmentId") Integer department) {
        Employee employee = null;
        try {
            employee = employeeService.getLowestPaidEmployee(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }
    @GetMapping(path = "/departments/all", params = "departmentId")
    public Object printEmployeesForDepartment(
            @RequestParam(value = "departmentId") Integer department) {
        List<Employee> employees = null;
        try {
            employees = employeeService.printEmployeesForDepartment(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }
    @GetMapping(path = "/departments/all")
    public Object printEmployeesByDepartments() {
        List<Employee> employees = null;
        try {
            employees = employeeService.printEmployeesByDepartments();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }
    @GetMapping(path = "/print")
    public Object printEmployees() {
        List<Employee> employees = null;
        try {
            employees = employeeService.printEmployees();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }
    @GetMapping(path = "/fill")
    public Object fillEmployeesList() {
        List<Employee> employees = null;
        try {
            employees = employeeService.fillEmployeesList();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }
}
