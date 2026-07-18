package com.example.Student_Management_System.Exception;

public class EmployeeNotFoundException extends  RuntimeException{
    public EmployeeNotFoundException(String e){
        super(e);
    }
}
