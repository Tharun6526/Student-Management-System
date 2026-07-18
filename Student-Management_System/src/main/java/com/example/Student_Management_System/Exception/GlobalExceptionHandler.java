package com.example.Student_Management_System.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleStudentNotFoundException(StudentNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp:", LocalDateTime.now());
        response.put("status:", HttpStatus.NOT_FOUND.value());
        response.put("message:" , e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
        Map<String, Object> res = new HashMap<>();
        res.put("TimeStamp: ", LocalDateTime.now());
        res.put("Status: ", HttpStatus.NOT_FOUND.value());
        res.put("Message: ", e.getMessage());
        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }


}