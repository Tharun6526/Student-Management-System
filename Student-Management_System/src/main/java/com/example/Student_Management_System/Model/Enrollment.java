package com.example.Student_Management_System.Model;
import com.example.Student_Management_System.ENUMs.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    private String grade;

    private Double attendance;

    private LocalDate enrollmentDate;


    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

}
