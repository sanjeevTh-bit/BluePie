package com.bluethink.EmployeeManagementProject.ServiceImpl;

import com.bluethink.EmployeeManagementProject.Entity.Employee;
import com.bluethink.EmployeeManagementProject.Repository.EmployeeRepo;
import com.bluethink.EmployeeManagementProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

//    @Override
//    public Employee addemployee(Employee employee) {
//
//        return employeeRepo.save(employee);
//    }


    @Override
    public List<Employee> addemployees(List<Employee> employees) {
        return employeeRepo.saveAll(employees);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public String removeEmployee(int id) {
        employeeRepo.deleteById(id);
        return "Employee Removed Successfully";
    }

    @Override
    public Optional<Employee> findEmpById(int id) {
        return employeeRepo.findById(id);
    }

    @Override
    public String updateEmployee(int id, Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(updatedEmployee.getName());
            employee.setAge(updatedEmployee.getAge());
            employee.setState(updatedEmployee.getState());
            employee.setType(updatedEmployee.getType());
            employee.setSalary(updatedEmployee.getSalary());

            employeeRepo.save(employee);

            return "Employee updated successfully";
        }else {
            return "employee not found";


        }
    }


}

