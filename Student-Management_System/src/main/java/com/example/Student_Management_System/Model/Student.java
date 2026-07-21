package com.example.Student_Management_System.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Address cannot be empty")
    private String address;

@ManyToOne
@JoinColumn(name="department_id")
private Department department;

    @Email(message = "Enter a valid email address")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must contain exactly 10 digits")
    private String phone;


    @Min(1)
    @Max(8)
    private int semester;

    private int marks;
    private int attendance;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();
}