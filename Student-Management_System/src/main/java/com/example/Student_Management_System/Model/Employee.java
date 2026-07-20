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
    @NotBlank(message = "Name must be Mentioned")
    private String Ename;
    @Email(message = "Incorrect Email Format")
    private String Eemail;
    @Pattern(regexp = "\\d{10}",message = "Phone Number is not Valid")
    private String phone;
    @NotBlank(message = "Address should not be empty")
    private  String address;
    @NotBlank(message = "Department Should be mentioned")
    private String Department;
}
