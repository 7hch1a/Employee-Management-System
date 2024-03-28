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

import com.ems.demo.models.Department;
import com.ems.demo.models.DepartmentDto;
import com.ems.demo.services.DepartmentsRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {
	
	@Autowired
	private DepartmentsRepository repo;
	
	@GetMapping({"","/"})
	public String showDepartmentList(Model model) {
		List<Department> departments = repo.findAll();
		model.addAttribute("departments",departments);
		return "departments/index";
	}
	
	@GetMapping({"/create"})
	public String showCreatePage(Model model) {
		DepartmentDto departmentDto = new DepartmentDto();
		model.addAttribute("departmentDto",departmentDto);
		return "departments/createDepartment";
	}
	
	@PostMapping("/create")
	public String createDepartment(
			@Valid @ModelAttribute DepartmentDto departmentDto,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "departments/createDepartment";
		}
		
		Department department = new Department();
		department.setDepartmentName(departmentDto.getDepartmentName());
		
		repo.save(department);
		
		return "redirect:/departments";
	}
	
	@GetMapping("/edit")
	public String showEditPage(
			Model model,
			@RequestParam Long id) {
		
		try {
			Department department = repo.findById(id).get();
			model.addAttribute("department", department);
			
			DepartmentDto departmentDto = new DepartmentDto();
			departmentDto.setDepartmentName(department.getDepartmentName());
			model.addAttribute("departmentDto", departmentDto);
			
		}catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			return "redirect:/departments";
		}
		
		return "departments/editDepartment";
	}
	
	@PostMapping("/edit")
	public String updateDepartment(
			Model model,
			@RequestParam long id,
			@Valid @ModelAttribute DepartmentDto departmentDto,
			BindingResult result) {
		
		try {
			Department department = repo.findById(id).get();
			model.addAttribute("department", department);
			
			if(result.hasErrors()) {
				return "departments/editDepartment";
			}
			
			department.setDepartmentName(departmentDto.getDepartmentName());
			
			repo.save(department);
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
			
		return "redirect:/departments";
	}
	
	@GetMapping("/delete")
	public String deleteDepartment(
			@RequestParam long id) {
		
		try {
			Department department = repo.findById(id).get();
			repo.delete(department);
			
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
		
		return "redirect:/departments";
	}
	
	
	
	
	
	
	

}
