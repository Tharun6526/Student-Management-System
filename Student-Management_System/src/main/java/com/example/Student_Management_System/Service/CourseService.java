package com.example.Student_Management_System.Service;

import com.example.Student_Management_System.DTO.AdminDTOs.CourseCreateDTO;
import com.example.Student_Management_System.DTO.AdminDTOs.CourseUpdateDTO;
import com.example.Student_Management_System.Model.Course;
import com.example.Student_Management_System.Model.Employee;
import com.example.Student_Management_System.Repository.CourseRepository;
import com.example.Student_Management_System.Repository.EmployeeRepo;
import com.example.Student_Management_System.Repository.EnrollementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private  final EmployeeRepo erepo;
    public CourseService(CourseRepository courseRepository,EmployeeRepo erepo){
        this.courseRepository =courseRepository;
        this.erepo  =erepo;
    }
    public Course add(CourseCreateDTO dto){
        Course s =  new Course();
        s.setCourseCode(dto.getCourseCode());
        s.setCourseName(dto.getCourseName());
        s.setCredits(dto.getCredits());
        s.setSemester(dto.getSemester());
        return courseRepository.save(s);
    }

    public Course updateCourse(Long id, CourseUpdateDTO c) {

        Course x = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course Not Found"));
        if(c.getCourseCode()!=null){
            x.setCourseCode(c.getCourseCode());
        }
        if(c.getCourseName()!=null){
            x.setCourseName(c.getCourseName());
        }
        if(c.getCredits()!=null){
            x.setCredits(c.getCredits());
        }
        if(c.getSemester()!=null){
            x.setSemester(c.getSemester());
        }
        return courseRepository.save(x);
    }

    public String deleteCourse(Long id) {
        Course s = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course Not Found"));
        courseRepository.delete(s);
        return "Course Deleted Successfully";
    }
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Course s = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course Not Found"));
        return s;
    }

    public List<Course> getCoursesBySemester(Integer semester) {
        return courseRepository.findAllBySemester(semester);
    }


    public List<Course> getEmployeeCourse(Long id) {
        Employee e =  erepo.findById(id).orElseThrow(()-> new RuntimeException("Employee Not Found"));
        return e.getCourse();
    }
}
