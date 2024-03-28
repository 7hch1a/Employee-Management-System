package com.ems.demo.models;

import jakarta.validation.constraints.NotEmpty;

public class DepartmentDto {

	private Long departmentId;
	
	@NotEmpty(message = "The department name is required")
	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	
}
