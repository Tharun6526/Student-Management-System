package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.StudentUpdate;
import com.example.Student_Management_System.Exception.StudentNotFoundException;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Repository.StudentRepo;

import org.springframework.stereotype.Service;

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
}
