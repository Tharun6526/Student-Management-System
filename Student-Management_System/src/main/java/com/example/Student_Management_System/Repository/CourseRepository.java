package com.example.Student_Management_System.Repository;

import com.example.Student_Management_System.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllBySemester(Integer semester);
}
