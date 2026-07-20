package com.example.Student_Management_System.Controller;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Service.EmployeeService;
import com.example.Student_Management_System.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final StudentService studentService;
    private final EmployeeService employeeService;

    public AdminController(StudentService studentService,
                           EmployeeService employeeService) {
        this.studentService = studentService;
        this.employeeService = employeeService;
    }
    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student s){
        Student x = studentService.addStudent(s);
            return new  ResponseEntity<>(x,HttpStatus.CREATED);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student x =  studentService.getProfile(id);
        return new ResponseEntity<>(x,HttpStatus.ACCEPTED);
    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> x  = studentService.getAllStudents();
        return  new ResponseEntity<>(x,HttpStatus.OK);
    }
    @PostMapping("/students/{id}/update")
    public ResponseEntity<Student> update(@PathVariable Long id ,@Valid @RequestBody Student s){
        Student x = studentService.updateStudent(id,s);
        return new ResponseEntity<>(x,HttpStatus.OK);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id){
        studentService.DeleteStudent(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
