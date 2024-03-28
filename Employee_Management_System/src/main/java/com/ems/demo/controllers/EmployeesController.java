package com.ems.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ems.demo.models.Department;
import com.ems.demo.models.DepartmentDto;
import com.ems.demo.models.Employee;
import com.ems.demo.services.DepartmentsRepository;
import com.ems.demo.services.EmployeeRepository;
import com.ems.demo.models.EmployeeDto;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentsRepository departmentsRepository;

	@GetMapping({ "", "/" })
	public String showEmployeeList(Model model) {
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("employees", employees);
		return "employees/index";
	}

	@GetMapping("/create")
	public String showCreatePage(Model model) {
		EmployeeDto employeeDto = new EmployeeDto();
		model.addAttribute("employeeDto", employeeDto);

		// Fetch department entities from the repository
		List<Department> departments = departmentsRepository.findAll();

		// Add the list of department DTOs to the model
		model.addAttribute("departments", departments);

		return "employees/createEmployee";
	}

	@PostMapping("/create")
	public String createEmployee(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult result) {

		if (result.hasErrors()) {
			return "employees/createEmployee";
		}

		Employee employee = new Employee();
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setEmployeePosition(employeeDto.getEmployeePosition());
		employee.setDepartmentId(employeeDto.getDepartmentId());

		employeeRepository.save(employee);

		return "redirect:/employees";
	}

	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam Long id) {

		try {
			Employee employee = employeeRepository.findById(id).orElseThrow(); // Change repo to employeeRepository
			model.addAttribute("employee", employee);

			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setEmployeeName(employee.getEmployeeName());
			employeeDto.setEmployeePosition(employee.getEmployeePosition());
			
			model.addAttribute("employeeDto", employeeDto);
			
			// Fetch department entities from the repository
			List<Department> departments = departmentsRepository.findAll();

			// Add the list of department DTOs to the model
			model.addAttribute("departments", departments);

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			return "redirect:/employees";
		}

		return "employees/editEmployee";
	}

	@PostMapping("/edit")
	public String updateEmployee(Model model, @RequestParam long id, @Valid @ModelAttribute EmployeeDto employeeDto,
			BindingResult result) {

		try {
			if (result.hasErrors()) {
				return "employees/editEmployee";
			}

			Employee employee = employeeRepository.findById(id).orElseThrow(); // Change repo to employeeRepository
			model.addAttribute("employee", employee);

			employee.setEmployeeName(employeeDto.getEmployeeName());
			employee.setEmployeePosition(employeeDto.getEmployeePosition());
			employee.setDepartmentId(employeeDto.getDepartmentId());
			
			employeeRepository.save(employee);

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}

		return "redirect:/employees";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam long id) {

		try {
			Employee employee = employeeRepository.findById(id).orElseThrow(); // Change repo to employeeRepository
			employeeRepository.delete(employee);

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}

		return "redirect:/employees";
	}

}
