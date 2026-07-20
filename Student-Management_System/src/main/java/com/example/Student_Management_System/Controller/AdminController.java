package com.example.Student_Management_System.Controller;
import com.example.Student_Management_System.DTO.AdminEmployeeUpdate;
import com.example.Student_Management_System.DTO.AdminStudentUpdate;
import com.example.Student_Management_System.DTO.EmployeeUpdate;
import com.example.Student_Management_System.Model.Employee;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Service.EmployeeService;
import com.example.Student_Management_System.Service.StudentService;
import jakarta.validation.Valid;
import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
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

    //Student Methods
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
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        studentService.DeleteStudent(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("/students/{id}/department")
    public ResponseEntity<Student> updateStudentProfile(@PathVariable Long id, @Valid @RequestBody AdminStudentUpdate s){
        Student x = studentService.updateStudentProfile(id,s);
        return  new ResponseEntity<>(x,HttpStatus.ACCEPTED);
    }
    @PostMapping("/employees/add")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp){
        return  new ResponseEntity<>(employeeService.add(emp),HttpStatus.CREATED);
    }
    @DeleteMapping("/employees/{id}/delete")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmp(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("employees/{id}/update")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long id, @RequestBody AdminEmployeeUpdate e){
        Employee x= employeeService.AdminUpdateEmp(id,e);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }


}
