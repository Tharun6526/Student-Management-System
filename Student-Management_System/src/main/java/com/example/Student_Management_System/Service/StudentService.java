package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.AdminDTOs.AdminStudentUpdate;
import com.example.Student_Management_System.DTO.StudentDTOs.StudentCreateDTO;
import com.example.Student_Management_System.DTO.StudentDTOs.StudentUpdate;
import com.example.Student_Management_System.Exception.DepartmentNotFoundException;
import com.example.Student_Management_System.Exception.StudentNotFoundException;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Department;
import com.example.Student_Management_System.Model.Enrollment;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Repository.DepartmentRepository;
import com.example.Student_Management_System.Repository.StudentRepo;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private  final StudentRepo studentRepo;
    private final DepartmentRepository departmentRepository;
    public StudentService(StudentRepo studentRepo,
                          DepartmentRepository departmentRepository) {
        this.studentRepo = studentRepo;
        this.departmentRepository = departmentRepository;
    }
    public Student getProfile( Long sId) {
         return studentRepo.findById(sId).orElseThrow(() -> new StudentNotFoundException("Student not Found"));
    }

    public Student updateDetails(Long id,StudentUpdate s) {

        Student m  = studentRepo.findById(id)
                                        .orElseThrow(() -> new StudentNotFoundException("Student not Found"));
        m.setAddress(s.getAddress());
        m.setEmail(s.getEmail());
        m.setPhone(s.getPhone());
      return  studentRepo.save(m);


    }
//ADmin Methods
    public Student addStudent(@Valid StudentCreateDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department Not Found"));

        Student student = new Student();

        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setSemester(dto.getSemester());
        student.setMarks(dto.getMarks());
        student.setAttendance(dto.getAttendance());

        student.setDepartment(department);

        return studentRepo.save(student);
    }


    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student updateStudent(Long id, Student s) {

        Student existing = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not Found"));

        BeanUtils.copyProperties(s, existing, "id");

        return studentRepo.save(existing);
    }

    public void deleteStudent(Long id) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        if (!student.getEnrollments().isEmpty()) {
            throw new RuntimeException(
                    "Cannot delete student because the student is enrolled in one or more courses."
            );
        }

        studentRepo.delete(student);
    }
    public Student updateStudentProfile(Long id, AdminStudentUpdate dto) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not Found"));

        if (dto.getName() != null)
            student.setName(dto.getName());

        if (dto.getEmail() != null)
            student.setEmail(dto.getEmail());

        if (dto.getPhone() != null)
            student.setPhone(dto.getPhone());

        if (dto.getAddress() != null)
            student.setAddress(dto.getAddress());

        if (dto.getSemester() != null)
            student.setSemester(dto.getSemester());

        if (dto.getDepartmentId() != null) {

            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));

            student.setDepartment(department);
        }

        return studentRepo.save(student);
    }

    public List<Course> getStudentCourses(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        return student.getEnrollments()
                .stream()
                .map(Enrollment::getCourse)
                .toList();
    }

    public List<Enrollment> getStudentEnrollments(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return student.getEnrollments();
    }
}
