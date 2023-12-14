package com.bluethink.EmployeeManagementProject;

import com.bluethink.EmployeeManagementProject.Controller.EmployeeController;
import com.bluethink.EmployeeManagementProject.Entity.Employee;
import com.bluethink.EmployeeManagementProject.ExceptionHandling.EmployeeNotFoundException;
import com.bluethink.EmployeeManagementProject.ServiceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private EmployeeController employeeController;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
  //  }

    @Test
    public void testAddEmployees() {
        // Mock the input data
        List<Employee> employees = new ArrayList<>();

        // Mock the behavior of the employeeService
        Mockito.when(employeeService.addemployees(Mockito.anyList())).thenReturn(employees);

        // Mock the BindingResult
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        // Call the controller method
        ResponseEntity<?> responseEntity = employeeController.addEmployees(employees, bindingResult);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(employees, responseEntity.getBody());
    }

    @Test
    public void testGetAllEmployees() {
        // Mock the behavior of the employeeService
        List<Employee> employeeList = new ArrayList<>();
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeList);

        // Call the controller method
        ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(employeeList, responseEntity.getBody());
    }
    @Test
    public void testFindEmployeeById() {
        // Mock the behavior of the employeeService
        int idToFind = 1;
        Employee mockEmployee = new Employee(); // Create a sample employee
        Mockito.when(employeeService.findEmpById(idToFind)).thenReturn(Optional.of(mockEmployee));

        // Call the controller method
        ResponseEntity<Employee> responseEntity = employeeController.findEmployeeById(idToFind);

        // Verify the response status is HttpStatus.OK
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the returned employee is the same as the mock employee
        Assertions.assertEquals(mockEmployee, responseEntity.getBody());
    }

    @Test
    public void testRemoveEmployee() {
        // Mock the behavior of the employeeService
        int idToRemove = 1;
        String resultMessage = "Employee removed successfully";
        Mockito.when(employeeService.removeEmployee(idToRemove)).thenReturn(resultMessage);

        // Call the controller method
        ResponseEntity<String> responseEntity = employeeController.removeEmployee(idToRemove);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(resultMessage, responseEntity.getBody());
    }







    @Test
    public void testUpdateEmployee() {
        // Mock the input data
        int idToUpdate = 1;
        Employee updatedEmployee = new Employee(); // Create a sample updated employee

        // Mock the behavior of the employeeService
        String resultMessage = "Employee updated successfully";
        Mockito.when(employeeService.updateEmployee(idToUpdate, updatedEmployee)).thenReturn(resultMessage);

        // Call the controller method
        ResponseEntity<String> responseEntity = employeeController.updateEmployee(idToUpdate, updatedEmployee);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(resultMessage, responseEntity.getBody());
    }
}

























