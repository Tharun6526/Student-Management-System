package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentCreateDTO;
import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentPatchDTO;
import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentUpdateAttendanceDTO;
import com.example.Student_Management_System.DTO.EnrollementDTOs.EnrollmentUpdateGradeDTO;
import com.example.Student_Management_System.ENUMs.EnrollmentStatus;
import com.example.Student_Management_System.Exception.CourseNotFoundException;
import com.example.Student_Management_System.Exception.EmployeeNotFoundException;
import com.example.Student_Management_System.Exception.EnrollmentNotFoundException;
import com.example.Student_Management_System.Exception.StudentNotFoundException;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Enrollment;
import com.example.Student_Management_System.Model.Student;
import com.example.Student_Management_System.Repository.CourseRepository;
import com.example.Student_Management_System.Repository.EnrollementRepository;
import com.example.Student_Management_System.Repository.StudentRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private  final EnrollementRepository enrolRepo;
    private  final StudentRepo srepo;
    private  final CourseRepository crepo;
    public EnrollmentService(EnrollementRepository enrolRepo,StudentRepo srepo,CourseRepository crepo){
        this.enrolRepo= enrolRepo;
        this.srepo = srepo;
        this.crepo = crepo;
    }

    public Enrollment add(EnrollmentCreateDTO dto)
    {
        Student s = srepo.findById(dto.getStudentId()).orElseThrow(()-> new StudentNotFoundException("Student Not Found"));
        Course c = crepo.findById(dto.getCourseId()).orElseThrow(()-> new CourseNotFoundException("Course Not Found"));
        Optional<Enrollment> existing = enrolRepo.findByStudentIdAndCourseId(dto.getStudentId(),dto.getCourseId());
        if(existing.isPresent()){
            throw new RuntimeException("Student is already enrolled in this course.");        }
        Enrollment e = new Enrollment();
        e.setStudent(s);
        e.setCourse(c);
        e.setEnrollmentDate(LocalDate.now());
        e.setStatus(EnrollmentStatus.ACTIVE);
        return enrolRepo.save(e);
    }


    public List<Enrollment> getStudentEnrols(Long id) {
        Student student = srepo.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student Not Found"));

        return enrolRepo.findByStudentId(student.getId());
    }

    public List<Course> getCourseEnrols(Long studentId) {

        Student student = srepo.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student Not Found"));

        return enrolRepo.findByStudentId(student.getId())
                .stream()
                .map(Enrollment::getCourse)
                .toList();
    }
    public void deleteEnrollment(Long id){
        Enrollment r =  enrolRepo.findById(id).orElseThrow(()->new EnrollmentNotFoundException("Could not found Enrollment"));
        enrolRepo.deleteById(id);
    }

    public Enrollment updateAttendance(Long id, EnrollmentUpdateAttendanceDTO dto) {
    Enrollment  e = enrolRepo.findById(id).orElseThrow(()->new EnrollmentNotFoundException("Enrollment Not Found"));
        e.setAttendance(dto.getAttendance());
        enrolRepo.save(e);
        return e;
    }

    public Enrollment updateGrade(Long id, EnrollmentUpdateGradeDTO s) {
        Enrollment  e = enrolRepo.findById(id).orElseThrow(()->new EnrollmentNotFoundException("Enrollment Not Found"));
        e.setGrade(s.getGrade());
        enrolRepo.save(e);
        return e;
    }
    public List<Enrollment> getAdminStudentEnrols(Long id) {
       Student s=  srepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found"));

        return enrolRepo.findByStudentId(s.getId());
    }

    public List<Enrollment> getAdminCourseEnrols(Long id) {

       Course c =  crepo.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course Not Found"));

        return enrolRepo.findByCourseId(c.getId());
    }

    public List<Enrollment> getEnrols() {
        return enrolRepo.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrolRepo.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
    }
    public Enrollment patchEnrollment(Long id, EnrollmentPatchDTO dto) {

        Enrollment enrollment = enrolRepo.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));

        if (dto.getAttendance() != null) {
            enrollment.setAttendance(dto.getAttendance());
        }

        if (dto.getGrade() != null) {
            enrollment.setGrade(dto.getGrade());
        }

        if (dto.getStatus() != null) {
            enrollment.setStatus(dto.getStatus());
        }

        return enrolRepo.save(enrollment);
    }
}
