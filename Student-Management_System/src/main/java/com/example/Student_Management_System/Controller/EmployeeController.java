package com.example.Student_Management_System.Controller;

import com.example.Student_Management_System.DTO.*;
import com.example.Student_Management_System.Model.Employee;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private  final EmployeeService service;
     public EmployeeController(EmployeeService employeeService){
         this.service =employeeService;
     }
     @GetMapping("/students/{id}")
     public ResponseEntity<Student> getStudentDetails(@PathVariable Long id) {
            Student x = service.getDetails(id);
            return  ResponseEntity.ok(x);
        }
    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student s){
        Student x =   service.addStudent(s);
        return new ResponseEntity<>(x,HttpStatus.CREATED);
    }
    @PutMapping("/students/{id}/updatemarks")
    public ResponseEntity<Student> updateMarks(@PathVariable Long id, @Valid @RequestBody StudentMarksUpdate s){

         Student n= service.updateMarks(id,s);
         return  ResponseEntity.ok(n);
    }
    @PutMapping("/students/{id}/attendance")
    public ResponseEntity<Student> updateAttendance(@PathVariable Long id, @RequestBody StudentAttendanceUpdate s){
         return ResponseEntity.ok(service.updateAttendance(id,s));
    }
    @PutMapping("/student/{id}/department")
    public ResponseEntity<Student> updateDepartment(@PathVariable Long id, @Valid@RequestBody StudentDepartmentUpdate s){
         Student x= service.updateDepartment(id,s);
         return ResponseEntity.ok(x);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> DeleteStudent( @PathVariable Long id){

         Student x = service.deleteStudent(id);
         return ResponseEntity.ok(x);
    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>> StudentsData(){
        List<Student> students = service.getStudents();
        return ResponseEntity.ok(students);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<Employee> UpdateDetails(@PathVariable Long id,@RequestBody EmployeeUpdate emp){
         Employee e = service.updateEmp(id,emp);
         return ResponseEntity.ok(e);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable Long id){
         Employee x = service.getEmp(id);
         return ResponseEntity.ok(x);
    }





}
