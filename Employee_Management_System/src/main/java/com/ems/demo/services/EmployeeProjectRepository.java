package com.ems.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ems.demo.models.Employee;
import com.ems.demo.models.EmployeeDeleteDataDto;
import com.ems.demo.models.EmployeeProject;
import com.ems.demo.models.EmployeeProjectId;


public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, EmployeeProjectId> {

	List<EmployeeProject> findAll();
	
	@Query("SELECT ep.employee.id FROM EmployeeProject ep WHERE ep.project.id = :projectId")
    List<Long> findEmployeeIdsByProjectId(Long projectId);

	@Query("SELECT DISTINCT e.employeeName\r\n"
		+ "FROM Employee e\r\n"
		+ "JOIN EmployeeProject ep ON e.employeeId = ep.employee.id\r\n"
		+ "WHERE ep.project.id = :projectId")
	List<String> findEmployeeNamesByProjectId(Long projectId);
	
	
	@Query("SELECT NEW com.ems.demo.models.EmployeeDeleteDataDto(ep.employee.id, e.employeeName) " +
	        "FROM EmployeeProject ep " +
	        "JOIN ep.employee e " +
	        "WHERE ep.project.id = :projectId")
	List<EmployeeDeleteDataDto> findEmployeeIdsAndNamesByProjectId(Long projectId);
	
	@Query("SELECT NEW com.ems.demo.models.EmployeeDeleteDataDto(e.id, e.employeeName, e.employeePosition, d.departmentName) " +
	        "FROM EmployeeProject ep " +
	        "JOIN ep.employee e " +
	        "JOIN e.department d " +
	        "WHERE ep.project.id = :projectId")
	List<EmployeeDeleteDataDto> findEmployeeDetailsByProjectId(Long projectId);


}
