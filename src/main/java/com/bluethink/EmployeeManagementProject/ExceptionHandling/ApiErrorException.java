package com.bluethink.EmployeeManagementProject.ExceptionHandling;

public class ApiErrorException extends RuntimeException {

    public ApiErrorException(String message) {
        super(message);
    }
}