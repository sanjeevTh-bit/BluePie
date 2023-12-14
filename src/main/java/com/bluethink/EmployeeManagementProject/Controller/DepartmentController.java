package com.bluethink.EmployeeManagementProject.Controller;

import com.bluethink.EmployeeManagementProject.Entity.Department;
import com.bluethink.EmployeeManagementProject.ExceptionHandling.DepartmentNotFoundException;
import com.bluethink.EmployeeManagementProject.ServiceImpl.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    //http://localhost:8888/api/departments/addDep


    @PostMapping("/addDept")
    public ResponseEntity<?> addDepartment(@Valid @RequestBody Department department, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Validation error: " + result.getFieldError().getDefaultMessage());
        }

        Department addedDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(addedDepartment, HttpStatus.OK);
    }


//    @PostMapping ("/addDep")
//    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
//        Department addedDepartment = departmentService.addDepartment(department);
//        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
//    }

    //http://localhost:8888/api/departments/removeDep/id

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeDepartment(@PathVariable int id) {
        String result = departmentService.removeDepartment(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //http://localhost:8888/api/departments/id

//    @GetMapping("/{id}")
//    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
//        Department department = departmentService.getDepartmentById(id);
//        if (department != null) {
//            return ResponseEntity.ok(department);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

    //http://localhost:8888/api/departments


    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            return department != null ?
                    ResponseEntity.ok(department) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            throw new DepartmentNotFoundException("Department not found with ID: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departmentList = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    //http://localhost:8888/api/departments/update/id

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable int id, @RequestBody Department updatedDepartment) {
        String result = departmentService.updateDepartment(id, updatedDepartment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}