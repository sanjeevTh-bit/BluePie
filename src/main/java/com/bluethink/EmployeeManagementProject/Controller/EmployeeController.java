package com.bluethink.EmployeeManagementProject.Controller;
import com.bluethink.EmployeeManagementProject.Entity.Employee;
import com.bluethink.EmployeeManagementProject.ExceptionHandling.EmployeeNotFoundException;
import com.bluethink.EmployeeManagementProject.ServiceImpl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    //http://localhost:8888/api/employees/addEmp
//    @PostMapping("/addEmp")
//    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
//        Employee addedEmployee = employeeService.addemployee(employee);
//        return new ResponseEntity<>(addedEmployee, HttpStatus.OK);
//
//    }

    //http://localhost:8888/api/employees/addEmp
    @PostMapping("/addEmp")
    public ResponseEntity<?> addEmployees(@Valid  @RequestBody List<Employee> employees,BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + result.getFieldError().getDefaultMessage());
        }

        List<Employee> addedEmployees = employeeService.addemployees(employees);
        return new ResponseEntity<>(addedEmployees, HttpStatus.OK);
    }


    //http://localhost:8888/api/employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList=employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    //http://localhost:8888/api/employees/removeEmp/id

    @DeleteMapping("/removeEmp/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable int id){
        String result =employeeService.removeEmployee(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    //http://localhost:8888/api/employees/id


    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) {
        try {
            Optional<Employee> employee = employeeService.findEmpById(id);
            return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> findEmployeeById(@PathVariable int id){
//        Optional<Employee> employee = employeeService.findEmpById(id);
//        return employee.map(value ->new ResponseEntity<>(value,HttpStatus.NOT_FOUND))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));



    //http://localhost:8888/api/employees/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id,@RequestBody Employee updatedEmployee ){
        String result = employeeService.updateEmployee(id,updatedEmployee);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}