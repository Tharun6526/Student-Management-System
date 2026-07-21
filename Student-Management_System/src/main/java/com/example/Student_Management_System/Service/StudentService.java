package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.AdminDTOs.AdminStudentUpdate;
import com.example.Student_Management_System.DTO.StudentDTOs.StudentUpdate;
import com.example.Student_Management_System.Exception.StudentNotFoundException;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Enrollment;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Repository.StudentRepo;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private  final StudentRepo studentRepo;
    public StudentService(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
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
    public Student addStudent(@Valid Student s) {
        Student x = new Student();
        BeanUtils.copyProperties(s,x);
        return studentRepo.save(x);
    }


    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student updateStudent(Long id,Student s) {
        Student x = studentRepo.findById(id).orElseThrow(()-> new StudentNotFoundException("Student not Found"));
        Student m = new Student();
        BeanUtils.copyProperties(s,m);
        return m;
    }

    public void deleteStudent(Long id) {
        Student x =  studentRepo.findById(id).orElseThrow(()->new StudentNotFoundException("Student Not Found"));
        studentRepo.deleteById(id);
    }
    public Student updateStudentProfile(Long id, AdminStudentUpdate s){
        Student temp = studentRepo.findById(id).orElseThrow(()->new StudentNotFoundException("Student not Found"));
        Student x = new Student();
        BeanUtils.copyProperties(x,temp);
        return x;
    }

    public List<Course> getStudentCourses(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getEnrollments()
                .stream()
                .map(Enrollment::getCourse)
                .toList();
    }

    public List<Enrollment> getStudentEnrollments(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getEnrollments();
    }
}
