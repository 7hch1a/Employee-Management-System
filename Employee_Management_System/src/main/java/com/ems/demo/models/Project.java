package com.ems.demo.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long projectId;
	    
	    private String projectName;
	    
	    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<EmployeeProject> employeeProjects = new HashSet<>();

//	    @ManyToMany(mappedBy = "projects")
//	    private Set<Employee> employees = new HashSet<>();
	    
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

//	    public Set<Employee> getEmployees() {
//	        return employees;
//	    }
//
//	    public void setEmployees(Set<Employee> employees) {
//	        this.employees = employees;
//	    }

}
