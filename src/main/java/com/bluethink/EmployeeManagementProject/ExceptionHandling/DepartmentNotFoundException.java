package com.bluethink.EmployeeManagementProject.ExceptionHandling;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String message) {super(message);
    }
}