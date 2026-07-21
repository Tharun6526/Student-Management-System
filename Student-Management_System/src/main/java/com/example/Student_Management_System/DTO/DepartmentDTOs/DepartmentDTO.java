package com.example.Student_Management_System.DTO.DepartmentDTOs;
import jakarta.validation.constraints.NotBlank;
import  lombok.Data;
@Data
public class DepartmentDTO {
    @NotBlank(message = "Department name is required")
    private String departmentName;

    @NotBlank(message = "HOD name is required")
    private String hodName;
}
