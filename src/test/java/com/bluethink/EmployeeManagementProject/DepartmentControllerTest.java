package com.bluethink.EmployeeManagementProject;

import com.bluethink.EmployeeManagementProject.Controller.DepartmentController;
import com.bluethink.EmployeeManagementProject.Entity.Department;
import com.bluethink.EmployeeManagementProject.ExceptionHandling.DepartmentNotFoundException;
import com.bluethink.EmployeeManagementProject.ServiceImpl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentServiceImpl departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDepartment() {
        // Mock the input data
        Department department = new Department();

        // Mock the behavior of the departmentService
        Mockito.when(departmentService.addDepartment(Mockito.any())).thenReturn(department);

        // Mock the BindingResult
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        // Call the controller method
        ResponseEntity<?> responseEntity = departmentController.addDepartment(department, bindingResult);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(department, responseEntity.getBody());
    }

    @Test
    public void testRemoveDepartment() {
        // Mock the behavior of the departmentService
        int idToRemove = 1;
        String resultMessage = "Department removed successfully";
        Mockito.when(departmentService.removeDepartment(idToRemove)).thenReturn(resultMessage);

        // Call the controller method
        ResponseEntity<String> responseEntity = departmentController.removeDepartment(idToRemove);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(resultMessage, responseEntity.getBody());
    }

    @Test
    public void testGetDepartmentByIdFound() {
        // Mock the behavior of the departmentService
        int idToFind = 1;
        Department mockDepartment = new Department(); // Create a sample department
        Mockito.when(departmentService.getDepartmentById(idToFind)).thenReturn(mockDepartment);

        // Call the controller method
        ResponseEntity<Department> responseEntity = departmentController.getDepartmentById(idToFind);

        // Verify the response status is HttpStatus.OK
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the returned department is the same as the mock department
        Assertions.assertEquals(mockDepartment, responseEntity.getBody());
    }

    @Test
    public void testGetDepartmentByIdNotFound() {
        // Mock the behavior of the departmentService
        int idToFind = 1;
        Mockito.when(departmentService.getDepartmentById(idToFind)).thenThrow(new DepartmentNotFoundException("Department not found with ID: " + idToFind));

        // Call the controller method
        Assertions.assertThrows(DepartmentNotFoundException.class, () -> departmentController.getDepartmentById(idToFind));
    }

    @Test
    public void testGetAllDepartments() {
        // Mock the behavior of the departmentService
        List<Department> departmentList = new ArrayList<>();
        Mockito.when(departmentService.getAllDepartments()).thenReturn(departmentList);

        // Call the controller method
        ResponseEntity<List<Department>> responseEntity = departmentController.getAllDepartments();

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(departmentList, responseEntity.getBody());
    }

    @Test
    public void testUpdateDepartment() {
        // Mock the input data
        int idToUpdate = 1;
        Department updatedDepartment = new Department(); // Create a sample updated department

        // Mock the behavior of the departmentService
        String resultMessage = "Department updated successfully";
        Mockito.when(departmentService.updateDepartment(idToUpdate, updatedDepartment)).thenReturn(resultMessage);

        // Call the controller method
        ResponseEntity<String> responseEntity = departmentController.updateDepartment(idToUpdate, updatedDepartment);

        // Verify the response
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(resultMessage, responseEntity.getBody());
    }
}
