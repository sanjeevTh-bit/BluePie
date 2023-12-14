package com.bluethink.EmployeeManagementProject.Repository;

import com.bluethink.EmployeeManagementProject.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {
}
