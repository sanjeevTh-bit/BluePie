package com.bluethink.EmployeeManagementProject;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bluethink.EmployeeManagementProject.Entity.Department;
import com.bluethink.EmployeeManagementProject.Entity.Employee;
import com.bluethink.EmployeeManagementProject.Repository.EmployeeRepo;
import com.bluethink.EmployeeManagementProject.ServiceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
//@EnableJpaRepositories(basePackageClasses = EmployeeRepo.class)
@DataJpaTest
public class EmployeeRepoTest {

    @Mock
    private EmployeeRepo employeeRepo;
    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    public void testFindById() {
        // Mock the behavior of the employeeRepo
        int idToFind = 1;
        Employee employee = new Employee(); // create a sample employee
        Mockito.when(employeeRepo.findById(idToFind)).thenReturn(Optional.of(employee));

        // Call the repository method
        Optional<Employee> result = employeeRepo.findById(idToFind);

        // Verify/ valiadte the result
        Assertions.assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    public void testSave() {
        // Mock the behavior of the employeeRepo
        Employee employeeToSave = new Employee(1, "John Doe", 30, "Active", "Full-Time", 50000); // create a sample employee to save
        Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(employeeToSave);

        // Call the repository method
        Employee result = employeeRepo.save(new Employee());

        // Verify the result
        assertEquals(employeeToSave, result);
    }

    @Test
    public void testDeleteById() {
        // Mock the behavior of the employeeRepo
        int idToDelete = 1;
        Mockito.doNothing().when(employeeRepo).deleteById(idToDelete);

        // Call the repository method
        employeeRepo.deleteById(idToDelete);

        // Verify that deleteById was called once with the correct ID
        Mockito.verify(employeeRepo, Mockito.times(1)).deleteById(idToDelete);
    }

    @Test
    public void testFindAll() {
// Create sample employees to save
        Employee sampleEmployee = new Employee(1,"Alice Johnson", 35, "London", "Contract", 60000);

        Employee anotherSampleEmployee = new Employee(2, "Another Employee", 28, "Some State", "Some Type", 45000);

        // Mock the behavior of the employeeRepo
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(sampleEmployee);
        employeeList.add(anotherSampleEmployee);
        Mockito.when(employeeRepo.findAll()).thenReturn(employeeList);

        // Call the repository method to save the sample employees
        Employee savedSampleEmployee = employeeRepo.save(sampleEmployee);
        Employee savedAnotherSampleEmployee = employeeRepo.save(anotherSampleEmployee);

        // Call the repository method to find all employees
        List<Employee> result = employeeRepo.findAll();

        // Verify the result
        assertEquals(employeeList.size(), result.size());
        assertEquals(employeeList, result);


    }



    @Test
    public void testUpdate() {
        // Mock the behavior of the employeeRepo
        int idToUpdate = 1;
        Employee existingEmployee = new Employee(1, "Alice Johnson", 35, "London", "Contract", 60000);
        Employee updatedEmployee = new Employee(1, "Ranny coel", 31, "Valley", "Part-Time", 55000);
        Mockito.when(employeeRepo.findById(idToUpdate)).thenReturn(Optional.of(existingEmployee));
        Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(updatedEmployee);

        // Call the service method that updates an employee
        String result = employeeService.updateEmployee(idToUpdate, updatedEmployee);

        // Verify the result
        Assertions.assertEquals("Employee updated successfully", result);
    }
}



























