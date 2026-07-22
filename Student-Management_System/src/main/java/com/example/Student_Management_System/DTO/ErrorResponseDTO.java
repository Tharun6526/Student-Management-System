package com.example.Student_Management_System.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDTO{

    private LocalDateTime timestamp;
    private int status;
    private String message;
}