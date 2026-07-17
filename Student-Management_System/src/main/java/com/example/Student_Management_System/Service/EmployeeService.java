package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.StudentAttendanceUpdate;
import com.example.Student_Management_System.DTO.StudentDepartmentUpdate;
import com.example.Student_Management_System.DTO.StudentMarksUpdate;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Repository.EmployeeRepo;
import com.example.Student_Management_System.Repository.StudentRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private  final EmployeeRepo repo;
    private  final StudentRepo srepo;

    public EmployeeService(EmployeeRepo employeeRepo,StudentRepo studentRepo){
        this.repo =employeeRepo;
        this.srepo = studentRepo;
    }

    //for getting student details
    public Student getDetails( Long id) {
        return srepo.findById(id).orElseThrow(()->new RuntimeException("Student not Found"));
    }

    public Student addStudent(Student s) {
       return srepo.save(s);
    }

    public Student updateMarks(Long id, @Valid StudentMarksUpdate s) {
        Student  m = srepo.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        m.setMarks(s.getMarks());
       return  srepo.save(m);
    }
    public Student updateAttendance(Long id, StudentAttendanceUpdate s){
        Student m = srepo.findById(id).orElseThrow(()->new RuntimeException("Id not Found"));
        m.setAttendance(s.getAttendance());
        return srepo.save(m);
    }

    public Student updateDepartment(Long id, StudentDepartmentUpdate s) {
        Student m = srepo.findById(id).orElseThrow(()-> new RuntimeException("Id not Found"));
        m.setDepartment(s.getDepartment());
        m.setSem(s.getSem());
        return srepo.save(m);
    }

    public Student deleteStudent(Long id) {
        Student m = srepo.findById(id).orElseThrow(()->new RuntimeException("Id not Found"));
        srepo.delete(m);
        return m;
    }

    public List<Student> getStudents() {
       return srepo.findAll();
    }
}
