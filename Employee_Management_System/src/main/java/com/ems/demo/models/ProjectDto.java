package com.ems.demo.models;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class ProjectDto {
	
//	@NotEmpty(message = "The id is required")
    private Long projectId;
	
	@NotEmpty(message = "The project name is required")
    private String projectName;
	
    private List<Long> employeeIds;


    public ProjectDto() {
    }

    public ProjectDto(Long projectId, String projectName, List<Long> employeeIds) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.employeeIds = employeeIds;
    }

    // Getters and setters

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
}
