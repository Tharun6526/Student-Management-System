package com.example.Student_Management_System.Controller;
import com.example.Student_Management_System.DTO.AdminDTOs.AdminEmployeeUpdate;
import com.example.Student_Management_System.DTO.AdminDTOs.AdminStudentUpdate;
import com.example.Student_Management_System.DTO.AdminDTOs.CourseCreateDTO;
import com.example.Student_Management_System.DTO.AdminDTOs.CourseUpdateDTO;
import com.example.Student_Management_System.DTO.DepartmentDTOs.DepartmentDTO;
import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentCreateDTO;
import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentPatchDTO;
import com.example.Student_Management_System.DTO.StudentDTOs.StudentCreateDTO;
import com.example.Student_Management_System.Model.*;
import com.example.Student_Management_System.Service.*;
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
    private  final CourseService courseService;
    private  final EnrollmentService enrollmentService;
    private  final DepartmentService departmentService;
    public AdminController(StudentService studentService,
                           EmployeeService employeeService,
                           CourseService courseService,
                            EnrollmentService enrollmentService,
                           DepartmentService departmentService) {
        this.studentService = studentService;
        this.employeeService = employeeService;
        this.courseService= courseService;
        this.enrollmentService = enrollmentService;
        this.departmentService = departmentService;
    }

    //Student Methods
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentCreateDTO s){
        Student x = studentService.addStudent(s);
            return new  ResponseEntity<>(x,HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> x  = studentService.getAllStudents();
        return  new ResponseEntity<>(x,HttpStatus.OK);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student x =  studentService.getProfile(id);
        return new ResponseEntity<>(x,HttpStatus.OK);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id ,@Valid @RequestBody Student s){
        Student x = studentService.updateStudent(id,s);
        return new ResponseEntity<>(x,HttpStatus.OK);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/students/{id}/department")
    public ResponseEntity<Student> updateStudentProfile(@PathVariable Long id, @Valid @RequestBody AdminStudentUpdate s){
        Student x = studentService.updateStudentProfile(id,s);
        return  ResponseEntity.ok(x);
    }
    @GetMapping("/students/{id}/courses")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable Long id) {
        List<Course> courses = studentService.getStudentCourses(id);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/students/{id}/enrollments")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(@PathVariable Long id) {
        List<Enrollment> enrollments = studentService.getStudentEnrollments(id);
        return ResponseEntity.ok(enrollments);
    }


    //Employee Methods
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp){
        return  new ResponseEntity<>(employeeService.add(emp),HttpStatus.CREATED);
    }
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmp(id));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long id,@Valid @RequestBody AdminEmployeeUpdate e){
        Employee x= employeeService.AdminUpdateEmp(id,e);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmp(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees/{id}/courses")
    public ResponseEntity<List<Course>> getCourse(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.getEmpCourses(id),HttpStatus.OK);
    }

//Course Methods
    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@Valid @RequestBody CourseCreateDTO c){
        return  new ResponseEntity<>(courseService.add(c),HttpStatus.CREATED);
    }
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,@Valid @RequestBody CourseUpdateDTO c){
            return new ResponseEntity<>(courseService.updateCourse(id,c),HttpStatus.OK);
    }
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/courses")
    public ResponseEntity<List<Course>>  getCourses(){
        return new ResponseEntity<>(courseService.getCourses(),HttpStatus.OK);
    }
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        return  new ResponseEntity<>(courseService.getCourseById(id),HttpStatus.OK);
    }
    @PostMapping("/enrollments")
    public ResponseEntity<Enrollment> addEnrollment(@Valid @RequestBody  EnrollmentCreateDTO dto){
        return new ResponseEntity<>(enrollmentService.add(dto),HttpStatus.CREATED);
    }
    @GetMapping("/enrollments")
    public ResponseEntity<List<Enrollment>> getEnrollments(){
        return new ResponseEntity<>(enrollmentService.getEnrols(),HttpStatus.OK);
    }
    @GetMapping("/enrollments/{id}")
    public ResponseEntity<Enrollment> getEnrollById(@PathVariable Long id){
        return  new ResponseEntity<>(enrollmentService.getEnrollmentById(id),HttpStatus.OK);
    }
    //get all enrollments data of student - id

    @PatchMapping("/enrollments/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(
            @PathVariable Long id,
            @RequestBody EnrollmentPatchDTO dto) {

        return ResponseEntity.ok(enrollmentService.patchEnrollment(id, dto));
    }
    //should return course enrollments data
    @GetMapping("/courses/{id}/enrollments")
    public ResponseEntity<List<Enrollment>> getAdminCourseEnrolls(@PathVariable Long id) {
        return new ResponseEntity<>(enrollmentService.getAdminCourseEnrols(id), HttpStatus.OK);
    }
    @DeleteMapping("/enrollments/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    //Department Methods
    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody DepartmentDTO department) {
        return new ResponseEntity<>(departmentService.add(department), HttpStatus.CREATED);
    }
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentDTO department) {

        return ResponseEntity.ok(departmentService.updateDepartment(id, department));
    }
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/departments/{id}/students")
    public ResponseEntity<List<Student>> getDepartmentStudents(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentStudents(id));
    }
    @GetMapping("/departments/{id}/employees")
    public ResponseEntity<List<Employee>> getDepartmentEmployees(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentEmployees(id));
    }
    @GetMapping("/departments/{id}/courses")
    public ResponseEntity<List<Course>> getDepartmentCourses(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentCourses(id));
    }


}
