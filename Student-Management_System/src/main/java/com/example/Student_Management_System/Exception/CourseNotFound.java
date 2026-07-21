package com.example.Student_Management_System.Exception;

public class CourseNotFound extends RuntimeException {
    public CourseNotFound(String message) {
        super(message);
    }
}
