package spring.restful_api.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.restful_api.demo.entities.Employee;
import spring.restful_api.demo.exceptions.EmployeeException;
import spring.restful_api.demo.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/sql-query")
    public ResponseEntity<List<Employee>> sqlQurey() {
        List<Employee> employees = employeeService.sqlQurey();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee-id")
    public ResponseEntity<Employee> getEmployeeById(@Param("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/add-employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee req) {
        Employee employee = employeeService.addEmployee(req);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update-employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee req) {
        Employee employee = employeeService.updateEmployee(req);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete-employee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@Param("id") Long id) {
        if (id == null) {
            throw new EmployeeException("Employee ID is null");
        }
        employeeService.deleteEmployee(id);
    }
}
