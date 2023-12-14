package com.bluethink.EmployeeManagementProject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 25,message = "Name cannot exceed 255 characters")
    private String Name;

    @Min(value = 18, message = "Age must be atleast 18")
    private int age;

    @NotBlank(message ="State is required")
    @Size(max = 255,message = "State cannot exceed 255 characters")
    private String state;


    @NotBlank(message = "Type is required")
    @Size(max = 255,message = "Type cannot exceed 255 characters")
    private String type;

    @Min(value = 0,message = "salary must be non-negative value")
    private int salary;



    public Employee(int id, String name, int age, String state, String type, int salary) {

        this.id = id;
        Name = name;
        this.age = age;
        this.state = state;
        this.type = type;
        this.salary = salary;
        this.department = department;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dep_id")
    @JsonBackReference
    private Department department;

    //Default Constructor
public Employee(){
    //Default constructor is needed for JPA
}

// Equals and hashCode methods for comparing instances
    // Update these based on the fields that should be considered for equality
//@Override
//public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//    Employee employee = (Employee) o;
//    return age == employee.age &&
//            Double.compare(employee.salary, salary) == 0 &&
//            Objects.equals(id, employee.id) &&
//            Objects.equals(Name, employee.Name) &&  // Corrected to match the field name
//            Objects.equals(state, employee.state) &&
//            Objects.equals(type, employee.type) &&
//            Objects.equals(department, employee.department);
//}
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id,Name, age, state, type, salary, department);
//    }
}
