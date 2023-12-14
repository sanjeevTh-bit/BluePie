package com.bluethink.EmployeeManagementProject;

import com.bluethink.EmployeeManagementProject.Entity.Department;
import com.bluethink.EmployeeManagementProject.Repository.DepartmentRepo;
import com.bluethink.EmployeeManagementProject.ServiceImpl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepo departmentRepo;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDepartment() {
        // Mock the input data
        Department department = new Department();

        // Mock the behavior of the departmentRepo
        Mockito.when(departmentRepo.save(Mockito.any(Department.class))).thenReturn(department);

        // Call the service method
        Department result = departmentService.addDepartment(department);

        // Verify the result
        Assertions.assertEquals(department, result);
    }

    @Test
    public void testRemoveDepartment() {
        // Mock the behavior of the departmentRepo
        int idToRemove = 1;
        Mockito.doNothing().when(departmentRepo).deleteById(idToRemove);

        // Call the service method
        String result = departmentService.removeDepartment(idToRemove);

        // Verify the result
        Assertions.assertEquals("Department removed successfully", result);
        // Verify that deleteById was called once with the correct ID
        Mockito.verify(departmentRepo).deleteById(idToRemove);
    }

    @Test
    public void testGetDepartmentById() {
        // Mock the behavior of the departmentRepo
        int idToFind = 1;
        Department department = new Department(); // create a sample department
        Mockito.when(departmentRepo.findById(idToFind)).thenReturn(Optional.of(department));

        // Call the service method
        Department result = departmentService.getDepartmentById(idToFind);

        // Verify the result
        Assertions.assertEquals(department, result);
    }

    @Test
    public void testGetAllDepartments() {
        // Mock the behavior of the departmentRepo
        List<Department> departmentList = new ArrayList<>();
        Mockito.when(departmentRepo.findAll()).thenReturn(departmentList);

        // Call the service method
        List<Department> result = departmentService.getAllDepartments();

        // Verify the result
        Assertions.assertEquals(departmentList, result);
    }

    @Test
    public void testUpdateDepartment() {
        // Mock the input data
        int idToUpdate = 1;
        Department updatedDepartment = new Department(); // create a sample updated department

        // Mock the behavior of the departmentRepo
        Optional<Department> existingDepartment = Optional.of(new Department()); // create an existing department
        Mockito.when(departmentRepo.findById(idToUpdate)).thenReturn(existingDepartment);
        Mockito.when(departmentRepo.save(Mockito.any(Department.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the service method
        String result = departmentService.updateDepartment(idToUpdate, updatedDepartment);

        // Verify the result
        Assertions.assertEquals("Department updated successfully", result);
    }
}
