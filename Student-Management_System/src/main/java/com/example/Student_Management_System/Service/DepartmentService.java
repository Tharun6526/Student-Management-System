package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.DepartmentDTOs.DepartmentDTO;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Department;
import com.example.Student_Management_System.Model.Employee;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private  final DepartmentRepository drepo;
    public DepartmentService(DepartmentRepository drepo){
        this.drepo =drepo;
    }
    public Department add(DepartmentDTO dept) {
        Department department = new Department();

        department.setDepartmentName(dept.getDepartmentName());
        department.setHodName(dept.getHodName());
        return drepo.save(department);
    }

    public List<Department> getDepartments() {
        return drepo.findAll();
    }

    public Department getDepartment(Long id) {
        return drepo.findById(id).orElseThrow(()-> new RuntimeException("Department Id Not Found"));
    }

    public Department updateDepartment(Long id, @Valid DepartmentDTO department) {
        Department d = drepo.findById(id).orElseThrow(()->new RuntimeException("Department Id Not Found"));
        d.setDepartmentName(department.getDepartmentName());
        d.setHodName(department.getHodName());
        return drepo.save(d);
    }

    public void deleteDepartment(Long id) {
        drepo.deleteById(id);
        
    }

    public List<Student> getDepartmentStudents(Long id) {
        Department d = drepo.findById(id).orElseThrow(()->new RuntimeException("Department Id Not Found"));
        return d.getStudents();
    }

    public List<Employee> getDepartmentEmployees(Long id) {
        Department d = drepo.findById(id).orElseThrow(()->new RuntimeException("Department Id Not Found"));
        return d.getEmployees();
    }

    public List<Course> getDepartmentCourses(Long id) {
        Department d = drepo.findById(id).orElseThrow(()->new RuntimeException("Department Id Not Found"));
        return d.getCourses();
    }
}
