package spring.restful_api.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.restful_api.demo.entities.Employee;
import spring.restful_api.demo.exceptions.EmployeeException;
import spring.restful_api.demo.services.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

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
    public ResponseEntity<Employee> getEmployeeById(@Param("id") UUID id) {
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employee-first-name")
    public ResponseEntity<List<Employee>> getEmployeeByFirstName(@Param("firstName") String firstName) {
        List<Employee> employees = employeeService.getEmployeeByFirstName(firstName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee-last-name")
    public ResponseEntity<List<Employee>> getEmployeeByLastName(@RequestBody String lastName) {
        List<Employee> employees = employeeService.getEmployeeByLastName(lastName);
        return ResponseEntity.ok(employees);
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
    public void deleteEmployee(@Param("id") UUID id) {
        if (id == null) {
            throw new EmployeeException("Employee ID is null");
        }
        employeeService.deleteEmployee(id);
    }
}
