package com.example.Student_Management_System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Min(value = 0, message = "Marks cannot be less than 0")
    @Max(value = 100, message = "Marks cannot be greater than 100")
    private int marks;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Department cannot be empty")
    private String department;

    @Email(message = "Enter a valid email address")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must contain exactly 10 digits")
    private String phone;

    @Min(value = 0, message = "Attendance cannot be less than 0")
    @Max(value = 100, message = "Attendance cannot be greater than 100")
    private int attendance;

    @NotBlank(message = "Semester cannot be empty")
    private String semester;
}