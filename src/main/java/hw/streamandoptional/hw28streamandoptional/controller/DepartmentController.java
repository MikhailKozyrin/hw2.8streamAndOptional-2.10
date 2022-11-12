package hw.streamandoptional.hw28streamandoptional.controller;

import hw.streamandoptional.hw28streamandoptional.model.Employee;
import hw.streamandoptional.hw28streamandoptional.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Object getHighestPaidEmployee(
            @RequestParam Integer department) {
        Employee employee = null;
        try {
            employee = departmentService.getHighestPaidEmployee(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/min-salary")
    public Object getLowestPaidEmployee(
            @RequestParam Integer department) {
        Employee employee = null;
        try {
            employee = departmentService.getLowestPaidEmployee(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/all", params = "departmentId")
    public Object printEmployeesForDepartment(
            @RequestParam Integer department) {
        List<Employee> employees = null;
        try {
            employees = departmentService.printEmployeesForDepartment(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

    @GetMapping(path = "/all")
    public Object printEmployeesByDepartments() {
        List<Employee> employees = null;
        try {
            employees = departmentService.printEmployeesByDepartments();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }
}

