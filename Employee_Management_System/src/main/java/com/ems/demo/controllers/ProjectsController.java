package com.ems.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.demo.models.Project; 
import com.ems.demo.models.ProjectDto;
import com.ems.demo.services.EmployeeProjectRepository;
import com.ems.demo.services.ProjectRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectsController {
	
	@Autowired
	private ProjectRepository repo;
	
	
	@GetMapping({"","/"})
    public String showProjectList(Model model) {
        List<Project> projects = repo.findAll();
        model.addAttribute("projects", projects);
        
        return "projects/index";
    }
	
	
	@GetMapping({"/create"})
	public String showCreatePage(Model model) {
		ProjectDto projectDto = new ProjectDto();
		model.addAttribute("projectDto",projectDto);
		return "projects/createProject";
	}
	
	@PostMapping("/create")
	public String createProject(
	        @Valid @ModelAttribute ProjectDto projectDto,
	        BindingResult result) {
	    
	    if (result.hasErrors()) {
	        return "projects/createProject";
	    }
	    
	    Project project = new Project();
	    project.setProjectName(projectDto.getProjectName());
	    
	    repo.save(project);
	    
	    return "redirect:/projects";
	}
	
	@GetMapping("/edit")
	public String showEditPage(
	        Model model,
	        @RequestParam Long id) {

	    try {
	        Project project = repo.findById(id).orElse(null);
	        if (project == null) {
	            // Redirect if the project is not found
	            return "redirect:/projects";
	        }

	        model.addAttribute("project", project);

	        ProjectDto projectDto = new ProjectDto();
	        projectDto.setProjectId(project.getProjectId());
	        projectDto.setProjectName(project.getProjectName());
	        model.addAttribute("projectDto", projectDto);

	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	        return "redirect:/projects";
	    }

	    return "projects/editProject";
	}
	
	@PostMapping("/edit")
	public String updateProject(
	        Model model,
	        @RequestParam long id,
	        @Valid @ModelAttribute ProjectDto projectDto,
	        BindingResult result) {

	    try {
	        Project project = repo.findById(id).orElse(null);
	        if (project == null) {
	            // Redirect if the project is not found
	            return "redirect:/projects";
	        }

	        model.addAttribute("project", project);

	        if (result.hasErrors()) {
	            return "projects/editProject";
	        }

	        project.setProjectName(projectDto.getProjectName());

	        repo.save(project);

	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	    }

	    return "redirect:/projects";
	}

	@GetMapping("/delete")
	public String deleteProject(@RequestParam long id) {

	    try {
	        Project project = repo.findById(id).orElse(null);
	        if (project != null) {
	            repo.delete(project);
	        }
	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	    }
	    
	    return "redirect:/projects";
	}



}
