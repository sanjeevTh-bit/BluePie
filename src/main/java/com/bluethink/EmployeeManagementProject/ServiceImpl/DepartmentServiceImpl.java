package com.bluethink.EmployeeManagementProject.ServiceImpl;

import com.bluethink.EmployeeManagementProject.Entity.Department;
import com.bluethink.EmployeeManagementProject.Repository.DepartmentRepo;
import com.bluethink.EmployeeManagementProject.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;
    @Override
    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public String removeDepartment(int id) {
        departmentRepo.deleteById(id);
        return "Department removed successfully";
    }

    @Override
    public Department getDepartmentById(int id) {
        Optional<Department> department = departmentRepo.findById(id);
        return department.orElse(null);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public String updateDepartment(int id, Department updatedDepartment) {
        Optional<Department> departmentOptional = departmentRepo.findById(id);

        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setDeptName(updatedDepartment.getDeptName());
            department.setDescription(updatedDepartment.getDescription());
            departmentRepo.save(department);

            return "Department updated successfully";
        } else {
            return "Department not found";
        }
    }










}
