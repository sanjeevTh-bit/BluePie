package com.bluethink.EmployeeManagementProject.Service;

import com.bluethink.EmployeeManagementProject.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  //  Employee addemployee(Employee employee);

    public List<Employee> addemployees(List<Employee> employees);

    List<Employee> getAllEmployees();

    String removeEmployee(int id);

    Optional<Employee> findEmpById(int id);

    String updateEmployee(int id , Employee updatedEmployee);
}
