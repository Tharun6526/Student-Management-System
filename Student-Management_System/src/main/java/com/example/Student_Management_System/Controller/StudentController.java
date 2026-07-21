package com.example.Student_Management_System.Controller;

import com.example.Student_Management_System.DTO.StudentDTOs.StudentUpdate;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Enrollment;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Service.CourseService;
import com.example.Student_Management_System.Service.EnrollmentService;
import com.example.Student_Management_System.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private  final EnrollmentService enrollmentService;
    public StudentController(StudentService studentService,CourseService courseService,EnrollmentService enrollmentService){
        this.studentService =studentService;
        this.courseService=  courseService;
        this.enrollmentService  = enrollmentService;
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<Student> getProfile( @PathVariable  Long id){
        Student x = studentService.getProfile(id);
      return ResponseEntity.ok(x);
    }
    @PutMapping("/profile/{id}")
    public ResponseEntity<Student> updateDetails( @PathVariable long id, @Valid @RequestBody StudentUpdate s){
    Student x =  studentService.updateDetails(id,s);
    return  ResponseEntity.ok(x);
    }
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(@RequestParam(required = false) Integer semester) {
        if(semester!=null){
        return  ResponseEntity.ok(courseService.getCoursesBySemester(semester));
        }
        return  ResponseEntity.ok(courseService.getCourses());
    }

    //get the courses assigned to student (st login)//student - id
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getMyStudentCourses(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getCourseEnrols(id));
    }

    @GetMapping("/{id}/enrollments")
    public ResponseEntity<List<Enrollment>> getMyStudentEnrollments(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getStudentEnrols(id));
    }



}
