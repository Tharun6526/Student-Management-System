package com.example.Student_Management_System.Exception;

public class CourseCannotBeDeletedException extends RuntimeException {
    public CourseCannotBeDeletedException(String message) {
        super(message);
    }
}
