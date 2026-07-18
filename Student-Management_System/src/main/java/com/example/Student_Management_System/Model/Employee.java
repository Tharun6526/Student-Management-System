package com.example.Student_Management_System.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
@Entity
public class Employee {
    @Id
    private Long Eid;
    @NotBlank
    private String Ename;
    @Email
    private String Eemail;
    @Pattern(regexp = "\\d{10}")
    private String phone;
    @NotBlank
    private  String address;
}
