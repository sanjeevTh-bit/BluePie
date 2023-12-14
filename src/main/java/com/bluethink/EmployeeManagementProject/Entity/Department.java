package com.bluethink.EmployeeManagementProject.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depId;

    @NotBlank(message = "Department name is required")
    @Size(max = 255, message = "Department name cannot exceed 255 characters")
    private String deptName;


    @NotBlank(message = "Description is required")
    private String description;



    public Department(int depId, String deptName, String description) {
        this.depId = depId;
        this.deptName = deptName;
        this.description = description;
    }

    @OneToMany(mappedBy = "department" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employees= new ArrayList<>();


    //Default Constructor
    public Department(){
        //Default constructor is needed for JPA
    }


}
