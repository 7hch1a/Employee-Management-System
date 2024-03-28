package com.ems.demo.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    private String employeeName;
    private String employeePosition;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    private Department department;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "employee_project",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

    // Constructors, getters, and setters
    
    public Employee() {
    }

    public Employee(String employeeName, String employeePosition, Department department) {
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.department = department;
    }

    // Getters and setters
    
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Long getDepartmentId() {
        if (department != null) {
            return department.getDepartmentId();
        }
        return null;
    }
    
    // Method to set department by ID
    public void setDepartmentId(Long departmentId) {
        this.department = new Department();
        this.department.setDepartmentId(departmentId);
    }
}
