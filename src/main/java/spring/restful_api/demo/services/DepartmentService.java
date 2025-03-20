package spring.restful_api.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.restful_api.demo.entities.Department;
import spring.restful_api.demo.exceptions.DepartmentException;
import spring.restful_api.demo.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentException(id));
    }

    public Department addDepartment(Department req) {
        return departmentRepository.save(req);
    }

    public Department updateDepartment(Department req) {
        Department department = departmentRepository.findById(req.getId()).orElseThrow(() -> new DepartmentException(req.getId()));
        department.setDepartmentName(req.getDepartmentName());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new DepartmentException(id);
        }
    }

}
