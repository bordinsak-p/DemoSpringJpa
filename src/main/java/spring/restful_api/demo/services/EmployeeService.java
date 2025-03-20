package spring.restful_api.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.restful_api.demo.entities.Department;
import spring.restful_api.demo.entities.Employee;
import spring.restful_api.demo.exceptions.EmployeeException;
import spring.restful_api.demo.repository.DepartmentRepository;
import spring.restful_api.demo.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeesRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getEmployees() {
        return employeesRepository.findAll();
    }

    public List<Employee> sqlQurey() {
        return employeesRepository.sqlQurey();
    }

    public Employee getEmployee(Long id) {
        return employeesRepository.findById(id).orElseThrow(() -> new EmployeeException(id));
    }

    public Employee addEmployee(Employee req) {
        return employeesRepository.save(req);
    }

    public Employee updateEmployee(Employee req) {
        Employee employee = employeesRepository.findById(req.getId())
                .orElseThrow(() -> new EmployeeException(req.getId()));

        employee.setFirstName(req.getFirstName());
        employee.setLastName(req.getLastName());

        Department department = employee.getDepartment();
        department.setDepartmentName(req.getDepartment().getDepartmentName());

        return employeesRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeesRepository.findById(id).orElseThrow(() -> new EmployeeException(id));
        employeesRepository.deleteById(employee.getId());
    }
}
