package com.ems.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ems.demo.models.Employee;
import com.ems.demo.models.EmployeeDeleteDataDto;
import com.ems.demo.models.EmployeeDto;
import com.ems.demo.models.EmployeeProject;
import com.ems.demo.models.EmployeeProjectDto;
import com.ems.demo.models.EmployeeProjectId;
import com.ems.demo.models.Project;
import com.ems.demo.services.EmployeeProjectRepository;
import com.ems.demo.services.EmployeeRepository;
import com.ems.demo.services.ProjectRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects/employees")
public class ProjectEmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeProjectRepository empRepo;
	@Autowired
	private ProjectRepository projectRepository;

	@GetMapping({ "", "/" })
	public String getEmployeesAndFilteredEmployeesByProjectId(@RequestParam("projectId") Long projectId, Model model) {
		

		// Fetch all employees
		List<Employee> allEmployees = employeeRepository.findAll();
		model.addAttribute("allEmployees", allEmployees);
		
		//Service method to find employee id and employee names for the provided project ID
		List<EmployeeDeleteDataDto> employeeIDsNamesPositionAndDepartmentName = empRepo.findEmployeeDetailsByProjectId(projectId);
		model.addAttribute("employeeIDsNamesPositionAndDepartmentName", employeeIDsNamesPositionAndDepartmentName);
		
		//Service method to find employee id and employee names for the provided project ID
		List<EmployeeDeleteDataDto> employeeIDsAndNames = empRepo.findEmployeeIdsAndNamesByProjectId(projectId);
		model.addAttribute("employeeIDsAndNames", employeeIDsAndNames);
	
	
		EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto();
		employeeProjectDto.setProjectId(projectId);
	    model.addAttribute("employeeProjectDto", employeeProjectDto);

		// Return the view name
		return "projects/employees";
	}

	/*
	 * @PostMapping("/add") public String addEmployee(@Valid @ModelAttribute
	 * EmployeeProjectDto employeeProjectDto, BindingResult result) { if
	 * (result.hasErrors()) { return "redirect:/projects/employees?projectId=" +
	 * employeeProjectDto.getProjectId(); }
	 * 
	 * // Retrieve or create the Employee entity based on employeeId Employee
	 * employee = employeeRepository.findById(employeeProjectDto.getEmployeeId())
	 * .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
	 * 
	 * // Retrieve or create the Project entity based on projectId in the DTO
	 * Project project =
	 * projectRepository.findById(employeeProjectDto.getProjectId()) .orElseThrow(()
	 * -> new IllegalArgumentException("Project not found"));
	 * 
	 * // Create an instance of EmployeeProjectId and set the IDs EmployeeProjectId
	 * id = new EmployeeProjectId(); id.setEmployee_id(employee.getEmployeeId());
	 * id.setProject_id(project.getProjectId());
	 * 
	 * // Create an instance of EmployeeProject and set the ID EmployeeProject
	 * employeeProject = new EmployeeProject(); employeeProject.setId(id);
	 * employeeProject.setEmployee(employee); employeeProject.setProject(project);
	 * 
	 * try { empRepo.save(employeeProject); } catch (DataRetrievalFailureException
	 * e) { // Log the error or handle it as needed
	 * System.err.println("Error occurred: " + e.getMessage()); // You can throw a
	 * custom exception or handle the error differently based on your application's
	 * requirements throw new
	 * RuntimeException("Failed to insert data into the database"); }
	 * 
	 * return "redirect:/projects/employees?projectId=" + id.getProject_id() ; //
	 * Redirect to the appropriate page after saving }
	 */
	
	
	 @PostMapping("/add")
    public ModelAndView addEmployee(@Valid @ModelAttribute EmployeeProjectDto employeeProjectDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("redirect:/projects/employees?projectId=" + employeeProjectDto.getProjectId());
            return modelAndView;
        }

        try {
        	
        	// Retrieve or create the Employee entity based on employeeId
    	    Employee employee = employeeRepository.findById(employeeProjectDto.getEmployeeId())
    	            .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    	    
    	    // Retrieve or create the Project entity based on projectId in the DTO
    	    Project project = projectRepository.findById(employeeProjectDto.getProjectId())
    	            .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    	    
    	    // Create an instance of EmployeeProjectId and set the IDs
    	    EmployeeProjectId id = new EmployeeProjectId();
    	    id.setEmployee_id(employee.getEmployeeId());
    	    id.setProject_id(project.getProjectId());
    	    
    	    // Create an instance of EmployeeProject and set the ID
    	    EmployeeProject employeeProject = new EmployeeProject();
    	    employeeProject.setId(id);
    	    employeeProject.setEmployee(employee);
    	    employeeProject.setProject(project);
    	    
            empRepo.save(employeeProject);
            modelAndView.setViewName("redirect:/projects/employees?projectId=" + employeeProjectDto.getProjectId());
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Failed to insert data into the database: " + e.getMessage();
            modelAndView.setViewName("redirect:/projects/employees?projectId=" + employeeProjectDto.getProjectId());
            modelAndView.addObject("errorMessage", errorMessage);
        }

        return modelAndView;
    }
	
	
	@PostMapping("/delete")
	public String deleteEmployee(@Valid @ModelAttribute EmployeeProjectDto employeeProjectDto, BindingResult result) {
	    if (result.hasErrors()) {
	    	return "redirect:/projects/employees?projectId=" + employeeProjectDto.getProjectId();
	    }
	    
	    // Retrieve or create the Employee entity based on employeeId
	    Employee employee = employeeRepository.findById(employeeProjectDto.getEmployeeId())
	            .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
	    
	    // Retrieve or create the Project entity based on projectId in the DTO
	    Project project = projectRepository.findById(employeeProjectDto.getProjectId())
	            .orElseThrow(() -> new IllegalArgumentException("Project not found"));
	    
	    // Create an instance of EmployeeProjectId and set the IDs
	    EmployeeProjectId id = new EmployeeProjectId();
	    id.setEmployee_id(employee.getEmployeeId());
	    id.setProject_id(project.getProjectId());
	    
	    // Create an instance of EmployeeProject and set the ID
	    EmployeeProject employeeProject = new EmployeeProject();
	    employeeProject.setId(id);
	    employeeProject.setEmployee(employee);
	    employeeProject.setProject(project);

	    // Save the EmployeeProject entity
	    empRepo.delete(employeeProject);

	    return "redirect:/projects/employees?projectId=" + id.getProject_id() ; // Redirect to the appropriate page after saving
	}




}
