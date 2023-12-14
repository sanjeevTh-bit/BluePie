package com.bluethink.EmployeeManagementProject.Service;

import com.bluethink.EmployeeManagementProject.Entity.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);

    String removeDepartment(int id);

    Department getDepartmentById(int id);

    List<Department> getAllDepartments();

    String updateDepartment(int id, Department updatedDepartment);






}
