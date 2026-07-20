package com.example.Student_Management_System.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class AdminEmployeeUpdate {
    private String ename;
    @Email(message = "Incorrect Email Format")
    private String eemail;
    @Pattern(regexp = "\\d{10}",message = "Phone Number is not Valid")
    private String phone;
    private  String address;
    private String Department;
}
