package com.example.Student_Management_System.Controller;

import com.example.Student_Management_System.DTO.StudentUpdate;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService =studentService;
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<Student> getProfile(@Valid @PathVariable  Long id){
        Student x = studentService.getProfile(id);
      return ResponseEntity.ok(x);
    }
    @PutMapping("/profile/{id}/update")
    public ResponseEntity<Student> updateDetails(@Valid @PathVariable long id, @Valid @RequestBody StudentUpdate s){
    Student x =  studentService.updateDetails(id,s);
    return  ResponseEntity.ok(x);
    }

}
