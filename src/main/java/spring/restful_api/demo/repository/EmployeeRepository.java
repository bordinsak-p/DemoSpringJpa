package spring.restful_api.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.restful_api.demo.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // select * from employee
    @Query("SELECT e FROM Employee e")
    List<Employee> sqlQurey();

    // select * from employee where first_name = ?
    List<Employee> findByFirstName(String firstName);

    // select * from employee where last_name = ?
    List<Employee> findByLastName(String lastName);
}
