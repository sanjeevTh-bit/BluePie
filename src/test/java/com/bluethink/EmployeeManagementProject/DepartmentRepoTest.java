package com.bluethink.EmployeeManagementProject;

import com.bluethink.EmployeeManagementProject.Entity.Department;
import com.bluethink.EmployeeManagementProject.Repository.DepartmentRepo;
import com.bluethink.EmployeeManagementProject.Repository.EmployeeRepo;
import com.bluethink.EmployeeManagementProject.ServiceImpl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
//@EnableJpaRepositories(basePackageClasses = EmployeeRepo.class)
@DataJpaTest
public class DepartmentRepoTest {
    @Mock
    private DepartmentRepo departmentRepo;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void testSave() {
        // Mock the input data
        Department departmentToSave = new Department();

        // Mock the behavior of the departmentRepo
        Mockito.when(departmentRepo.save(departmentToSave)).thenReturn(departmentToSave);

        // Call the repository method
        Department savedDepartment = departmentRepo.save(departmentToSave);

        // Verify the result
        Assertions.assertEquals(departmentToSave, savedDepartment);
    }




    @Test
    public void testFindById() {
        // Mock the input data
        int idToFind = 1;
        Department expectedDepartment = new Department();

        // Mock the behavior of the departmentRepo
        Mockito.when(departmentRepo.findById(idToFind)).thenReturn(Optional.of(expectedDepartment));

        // Call the repository method
        Optional<Department> foundDepartment = departmentRepo.findById(idToFind);

        // Verify the result
        Assertions.assertEquals(expectedDepartment, foundDepartment.orElse(null));
    }

    @Test
    public void testFindAll() {
        // Mock the behavior of the departmentRepo
        List<Department> expectedDepartments = List.of(new Department(), new Department());
        Mockito.when(departmentRepo.findAll()).thenReturn(expectedDepartments);

        // Call the repository method
        List<Department> foundDepartments = departmentRepo.findAll();

        // Verify the result
        Assertions.assertEquals(expectedDepartments, foundDepartments);
    }

    @Test
    public void testUpdateDepartment() {
        // Mock the behavior of the departmentRepo
        int idToUpdate = 1;
        Department existingDepartment = new Department(1, "Human Resource", "Department focused on managing personnel, recruitment, and employee relations");
        Department updatedDepartment = new Department(1, "Updated Human Resource ", "Updated Department focused on managing personnel, recruitment, and employee relations");
        Mockito.when(departmentRepo.findById(idToUpdate)).thenReturn(Optional.of(existingDepartment));
        Mockito.when(departmentRepo.save(Mockito.any(Department.class))).thenReturn(updatedDepartment);

        // Call the service method that updates a department
        String result = departmentService.updateDepartment(idToUpdate, updatedDepartment);

        // Verify the result
        Assertions.assertEquals("Department updated successfully", result);
    }
//
//    // Add more test cases as needed for other repository methods
@Test
public void testDeleteDepartmentById() {
    // Mock data
    int departmentIdToDelete = 1;

    // Mock the behavior of the departmentRepo
    Mockito.doNothing().when(departmentRepo).deleteById(departmentIdToDelete);

    // Call the repository method
    departmentRepo.deleteById(departmentIdToDelete);

    // Verify that deleteById was called once with the correct ID
    Mockito.verify(departmentRepo, Mockito.times(1)).deleteById(departmentIdToDelete);
}

}
