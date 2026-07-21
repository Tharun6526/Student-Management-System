package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.AdminDTOs.AdminEmployeeUpdate;
import com.example.Student_Management_System.DTO.EmployeeDTOs.EmployeeUpdate;
import com.example.Student_Management_System.DTO.EmployeeDTOs.EmployeeStudentAttendanceUpdate;
import com.example.Student_Management_System.DTO.EmployeeDTOs.EmployeeStudentMarksUpdate;
import com.example.Student_Management_System.Exception.EmployeeDeletionNotAllowedException;
import com.example.Student_Management_System.Exception.EmployeeNotFoundException;
import com.example.Student_Management_System.Exception.StudentNotFoundException;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Department;
import com.example.Student_Management_System.Model.Employee;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Repository.DepartmentRepository;
import com.example.Student_Management_System.Repository.EmployeeRepo;
import com.example.Student_Management_System.Repository.StudentRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private  final EmployeeRepo repo;
    private  final StudentRepo srepo;
    private  final DepartmentRepository departmentRepository;
    public EmployeeService(EmployeeRepo employeeRepo,StudentRepo studentRepo,DepartmentRepository deptRepo){
        this.repo =employeeRepo;
        this.srepo = studentRepo;
        this.departmentRepository= deptRepo;
    }

    //for getting student details
    public Student getDetails( Long id) {
        return srepo.findById(id).orElseThrow(()->new StudentNotFoundException("Student not Found"));
    }

    public Student addStudent(Student s) {
       return srepo.save(s);
    }

    public Student updateMarks(Long id, @Valid EmployeeStudentMarksUpdate s) {
        Student  m = srepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Id not found"));
        m.setMarks(s.getMarks());
       return  srepo.save(m);
    }
    public Student updateAttendance(Long id, EmployeeStudentAttendanceUpdate s){
        Student m = srepo.findById(id).orElseThrow(()->new StudentNotFoundException("Id not Found"));
        m.setAttendance(s.getAttendance());
        return srepo.save(m);
    }


    public Student deleteStudent(Long id) {
        Student m = srepo.findById(id).orElseThrow(()->new StudentNotFoundException("Id not Found"));
        srepo.delete(m);
        return m;
    }

    public List<Student> getStudents() {
       return srepo.findAll();
    }

    public Employee updateEmp(Long id, EmployeeUpdate emp) {
        Employee x = repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee Id Not Found"));
        x.setEemail(emp.getEmail());
        x.setPhone(emp.getPhone());
        x.setAddress(emp.getAddress());
        return repo.save(x);
    }
    public Employee getEmp(Long id){
        Employee x =  repo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee id Not Found"));
        return x;
    }

    //Admin Methods
    public Employee add(Employee e){
        return repo.save(e);
    }

    public void deleteEmp(Long id) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        if (!emp.getCourse().isEmpty()) {
            throw new EmployeeDeletionNotAllowedException(
                    "Cannot delete employee because they are assigned to courses."
            );
        }

        repo.delete(emp);
    }

    public Employee AdminUpdateEmp(Long id, AdminEmployeeUpdate e) {
        Employee m = repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
        Department d =  new Department();
        if (e.getEname() != null) {
            m.setEname(e.getEname());
        }

        if (e.getEemail() != null) {
            m.setEemail(e.getEemail());
        }

        if (e.getPhone() != null) {
            m.setPhone(e.getPhone());
        }

        if (e.getAddress() != null) {
            m.setAddress(e.getAddress());
        }

        if (e.getDepartmentId() != null) {
            Department department =  departmentRepository.findById(e.getDepartmentId()).orElseThrow(()->new RuntimeException("Department Not Found"));
            m.setDepartment(department);
        }
        return  repo.save(m);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public List<Course> getEmpCourses(Long id) {
        Employee m = repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
        return m.getCourse();
    }
}
