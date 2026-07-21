package com.example.Student_Management_System.DTO.AdminDTOs;

import lombok.Data;

    @Data
    public class CourseUpdateDTO {
        private String courseCode;
        private String courseName;
        private Integer credits;
        private Integer semester;
    }
