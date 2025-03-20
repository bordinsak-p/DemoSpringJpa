package spring.restful_api.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.restful_api.demo.entities.Department;
import spring.restful_api.demo.entities.Employee;
import spring.restful_api.demo.services.EmployeeService;

@SpringBootTest
class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    private Employee employeeData() {
        Department department = new Department();
        department.setDepartmentName("SDD");

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDepartment(department);

        return employee;
    }

    @Test
    @Order(1)
     void createEmployees() {
        Employee employee = employeeService.getEmployee(1L);
        employee.setFirstName("test1");
        employee.setLastName("test2");

        // save the employee
        Employee employeeSave = employeeService.addEmployee(employee);

        // check equality of the two objects
        Assertions.assertEquals(employee, employeeSave);
    }

    @Test
    @Order(2)
    void updateEmployees() {

        Employee employee = employeeData();
        employee.setFirstName("Bordinsak123");
        employee.setId(1L);

        // update the employee
        Employee employeeUpdate = employeeService.updateEmployee(employee);


        // check equality of the two objects
        Assertions.assertEquals(employee, employeeUpdate);
    }
}


