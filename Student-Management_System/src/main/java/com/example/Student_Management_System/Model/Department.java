package com.example.Student_Management_System.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departmentName;

    private String hodName;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private  List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();
}
