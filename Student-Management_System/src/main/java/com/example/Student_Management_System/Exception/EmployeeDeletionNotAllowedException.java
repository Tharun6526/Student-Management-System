package com.example.Student_Management_System.Exception;

public class EmployeeDeletionNotAllowedException extends RuntimeException {
    public EmployeeDeletionNotAllowedException(String message) {
        super(message);
    }
}
