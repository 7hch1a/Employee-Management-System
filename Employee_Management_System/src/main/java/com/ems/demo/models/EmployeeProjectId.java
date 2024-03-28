package com.ems.demo.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeProjectId implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Long employee_id;
    private Long project_id;
    
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public Long getProject_id() {
		return project_id;
	}
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}
    
	

    
}
