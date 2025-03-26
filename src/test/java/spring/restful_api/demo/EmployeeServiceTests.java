package spring.restful_api.demo;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.restful_api.demo.entities.Employee;
import spring.restful_api.demo.exceptions.EmployeeException;
import spring.restful_api.demo.services.EmployeeService;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // ทำให้ทุก @Test ใช้ instance เดียวกัน
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @Setter
    @Getter
    private UUID empSaveId = null;

    @Test
    @Order(1)
    void addEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        Assertions.assertNull(employee.getId());
        Employee saved = employeeService.addEmployee(employee);
        setEmpSaveId(saved.getId());
        Assertions.assertNotNull(saved.getId());
    }

    @Test
    @Order(2)
    void getEmployee() {
        List<Employee> employees = employeeService.getEmployees();
        Assertions.assertNotNull(employees);
    }

    @Test
    @Order(3)
    void getEmployeeById() {
        Employee employees = employeeService.getEmployee(getEmpSaveId());
        Assertions.assertNotNull(employees);
    }

    @Test
    @Order(5)
    void getEmployeeIdByPath() {
        Employee employees = employeeService.getEmployee(getEmpSaveId());
        Assertions.assertNotNull(employees);
    }

    @Test
    @Order(6)
    void getEmployeeByFirstName() {
        List<Employee> employees = employeeService.getEmployeeByFirstName("John");
        Assertions.assertNotNull(employees);
    }

    @Test
    @Order(7)
    void getEmployeeByLastName() {
        List<Employee> employees = employeeService.getEmployeeByLastName("Doe");
        Assertions.assertNotNull(employees);
    }

    @Test
    @Order(8)
    void updateEmployee() {
        Employee employee = employeeService.getEmployee(getEmpSaveId());
        employee.setFirstName("Jane");
        employee.setLastName("Doe");
        Employee updated = employeeService.updateEmployee(employee);
        Assertions.assertEquals(employee, updated);
    }

    @Test
    @Order(9)
    void deleteEmployee() {
        Employee employee = employeeService.getEmployee(getEmpSaveId());
        employeeService.deleteEmployee(employee.getId());

        EmployeeException exception = Assertions.assertThrows(EmployeeException.class, () -> {
            employeeService.getEmployee(employee.getId());
        });
        Assertions.assertTrue(exception.getMessage().contains("Could not find employee ID : " + employee.getId()));
    }

 }
