package com.ems.demo.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeDto {

	@NotEmpty(message = "The employee name is required")
	private String employeeName;
	
	@NotEmpty(message = "The employee position is required")
	private String employeePosition;
    
	@NotNull(message = "The department ID is required")
	private Long departmentId; // Assuming departmentId is of type Long

    
    // Constructor
    public EmployeeDto() {
    }

    // Getters and setters
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
