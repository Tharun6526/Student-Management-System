package com.example.Student_Management_System.Controller;

import com.example.Student_Management_System.DTO.EmployeeDTOs.EmployeeStudentAttendanceUpdate;
import com.example.Student_Management_System.DTO.EmployeeDTOs.EmployeeStudentMarksUpdate;
import com.example.Student_Management_System.DTO.EmployeeDTOs.EmployeeUpdate;
import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentUpdateAttendanceDTO;
import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentUpdateGradeDTO;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Employee;
import com.example.Student_Management_System.Model.Enrollment;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Service.CourseService;
import com.example.Student_Management_System.Service.EmployeeService;
import com.example.Student_Management_System.Service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private  final EmployeeService service;
    private final CourseService courseService;
    private  final EnrollmentService enrollmentService;

     public EmployeeController(EmployeeService employeeService,EnrollmentService enrollmentService,CourseService courseService){
         this.service =employeeService;
         this.enrollmentService = enrollmentService;
         this.courseService = courseService;
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
    @PatchMapping("/students/{id}/marks")
    public ResponseEntity<Student> updateMarks(@PathVariable Long id, @Valid @RequestBody EmployeeStudentMarksUpdate s){

         Student n= service.updateMarks(id,s);
         return  ResponseEntity.ok(n);
    }
    @PatchMapping("/students/{id}/attendance")
    public ResponseEntity<Student> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeStudentAttendanceUpdate dto) {

        Student student = service.updateAttendance(id, dto);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> StudentsData(){
        List<Student> students = service.getStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> DeleteStudent( @PathVariable Long id){

         Student x = service.deleteStudent(id);
         return ResponseEntity.ok(x);
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

    //enrollement methods
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getFacultyCourse(@PathVariable Long id){
         return new ResponseEntity<>(courseService.getEmployeeCourse(id),HttpStatus.OK);
    }
    @PatchMapping("/enrollments/{id}/attendance")
    public ResponseEntity<Enrollment> updateAttendance(@PathVariable Long id, @RequestBody EnrollmentUpdateAttendanceDTO s){
        Enrollment updated =enrollmentService.updateAttendance(id,s);
         return new  ResponseEntity<>(updated,HttpStatus.OK);
    }

    @PatchMapping("/enrollments/{id}/grade")
    public ResponseEntity<Enrollment> updateGrade(@PathVariable Long id, @RequestBody EnrollmentUpdateGradeDTO s){
        Enrollment updated =enrollmentService.updateGrade(id,s);

        return new  ResponseEntity<>(updated,HttpStatus.OK);
    }







}
