package com.example.Student_Management_System.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.type.descriptor.java.SerializableJavaType;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;

    private String courseName;

    private int credits;

    private Integer semester;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments =  new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
