package spring.restful_api.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.restful_api.demo.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
