package com.bluethink.EmployeeManagementProject.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error: " + e.getMessage());
    }

    @ExceptionHandler({EmployeeNotFoundException.class, DepartmentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Not Found: " + e.getMessage());
    }

    @ExceptionHandler(ApiErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleApiErrorException(ApiErrorException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("API Error: " + e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(BindException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Validation Error: " + e.getFieldError().getDefaultMessage());
    }

    // Add more exception handling methods as needed
}
