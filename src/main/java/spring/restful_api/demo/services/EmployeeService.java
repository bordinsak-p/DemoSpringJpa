package spring.restful_api.demo.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.restful_api.demo.entities.Employee;
import spring.restful_api.demo.exceptions.EmployeeException;
import spring.restful_api.demo.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeesRepository;

    public List<Employee> getEmployees() {
        return employeesRepository.findAll();
    }

    public List<Employee> sqlQurey() {
        return employeesRepository.sqlQurey();
    }

    public Employee getEmployee(Long id) {
        return employeesRepository.findById(id).orElseThrow(() -> new EmployeeException(id));
    }

    public List<Employee> getEmployeeByFirstName(String firstName) {
        return employeesRepository.findByFirstName(firstName);
    }

    public List<Employee> getEmployeeByLastName(String lastName) {
        return employeesRepository.findByLastName(lastName);
    }

    @Transactional
    public Employee addEmployee(Employee req) {
        return employeesRepository.save(req);
    }

    @Transactional
    public Employee updateEmployee(Employee req) {
        Employee employee = employeesRepository.findById(req.getId()).orElseThrow(() -> new EmployeeException(req.getId()));
        employee.setFirstName(req.getFirstName());
        employee.setLastName(req.getLastName());
        return employeesRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeesRepository.findById(id).orElseThrow(() -> new EmployeeException(id));
        employeesRepository.delete(employee);
    }
}
