package com.example.Student_Management_System.DTO.AdminDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class AdminEmployeeUpdate {
    @Size(min = 3, max = 50)
    private String ename;
    @Email(message = "Incorrect Email Format")
    private String eemail;
    @Pattern(regexp = "\\d{10}",message = "Phone Number is not Valid")
    private String phone;
    private  String address;
    @NotBlank(message = "Department is required")
    private Long departmentId;
}
