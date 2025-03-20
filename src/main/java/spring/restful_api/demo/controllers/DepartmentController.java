package spring.restful_api.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import spring.restful_api.demo.entities.Department;
import spring.restful_api.demo.services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Get all departments
    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    // Get department by id
    @GetMapping("/department-id")
    public Department getDepartmentById(@Param("id") Long id) {
        return departmentService.getDepartment(id);
    }

    // Get department by id using path variable
    @GetMapping("/department-id/{id}")
    public Department getDepartmentByIdPath(@PathVariable("id") Long id) {
        return departmentService.getDepartment(id);
    }

    // Add department
    @PostMapping("/add-department")
    public Department addDepartment(@RequestBody Department req) {
        return departmentService.addDepartment(req);
    }

    // Update department
    @PutMapping("/update-department")
    public Department updateDepartment(@RequestBody Department req) {
        return departmentService.updateDepartment(req);
    }

    // Delete department
    @DeleteMapping("/delete-department")
    public void deleteDepartment(@Param("id") Long id) {
        departmentService.deleteDepartment(id);
    }
}
