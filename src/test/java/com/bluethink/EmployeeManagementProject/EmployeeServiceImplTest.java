package com.bluethink.EmployeeManagementProject;

import com.bluethink.EmployeeManagementProject.Entity.Employee;
import com.bluethink.EmployeeManagementProject.Repository.EmployeeRepo;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void testAddEmployees() {
        // Mock the input data
        List<Employee> employees = new ArrayList<>();

        // Mock the behavior of the employeeRepo
        Mockito.when(employeeRepo.saveAll(Mockito.anyList())).thenReturn(employees);

        // Call the service method
        List<Employee> result = employeeService.addemployees(employees);

        // Verify the result
        Assertions.assertEquals(employees, result);
    }

    @Test
    public void testGetAllEmployees() {
        // Mock the behavior of the employeeRepo
        List<Employee> employeeList = new ArrayList<>();
        Mockito.when(employeeRepo.findAll()).thenReturn(employeeList);

        // Call the service method
        List<Employee> result = employeeService.getAllEmployees();

        // Verify the result
        Assertions.assertEquals(employeeList, result);
    }

    @Test
    public void testRemoveEmployee() {
        // Mock the behavior of the employeeRepo
        int idToRemove = 1;
        Mockito.doNothing().when(employeeRepo).deleteById(idToRemove);

        // Call the service method
        String result = employeeService.removeEmployee(idToRemove);

        // Verify the result
        Assertions.assertEquals("Employee Removed Successfully", result);
        // Verify that deleteById was called once with the correct ID
        Mockito.verify(employeeRepo, Mockito.times(1)).deleteById(idToRemove);
    }

    @Test
    public void testFindEmpById() {
        // Mock the behavior of the employeeRepo
        int idToFind = 1;
        Employee employee = new Employee(); // create a sample employee
        Mockito.when(employeeRepo.findById(idToFind)).thenReturn(Optional.of(employee));

        // Call the service method
        Optional<Employee> result = employeeService.findEmpById(idToFind);

        // Verify the result
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(employee, result.get());
    }

    @Test
    public void testUpdateEmployee() {
        // Mock the input data
        int idToUpdate = 1;
        Employee updatedEmployee = new Employee(); // create a sample updated employee

        // Mock the behavior of the employeeRepo
        Optional<Employee> existingEmployee = Optional.of(new Employee()); // create an existing employee
        Mockito.when(employeeRepo.findById(idToUpdate)).thenReturn(existingEmployee);
        Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the service method
        String result = employeeService.updateEmployee(idToUpdate, updatedEmployee);

        // Verify the result
        Assertions.assertEquals("Employee updated successfully", result);
    }

}
