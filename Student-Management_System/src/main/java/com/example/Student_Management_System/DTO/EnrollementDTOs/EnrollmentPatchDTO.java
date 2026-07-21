package com.example.Student_Management_System.DTO.EnrollementDTOs;

import com.example.Student_Management_System.ENUMs.EnrollmentStatus;
import lombok.Data;

@Data
public class EnrollmentPatchDTO {

    private Double attendance;
    private String grade;
    private EnrollmentStatus status;
}