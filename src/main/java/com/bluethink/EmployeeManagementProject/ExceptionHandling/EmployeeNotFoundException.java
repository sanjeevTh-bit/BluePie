package com.bluethink.EmployeeManagementProject.ExceptionHandling;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {super(message);
    }
}