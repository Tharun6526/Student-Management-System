package com.example.Student_Management_System.DTO.StudentDTOs;
import lombok.Data;
@Data
public class StudentCreateDTO {

    private String name;
    private String address;
    private String email;
    private String phone;
    private Integer semester;
    private Integer marks;
    private Integer attendance;

    private Long departmentId;
}