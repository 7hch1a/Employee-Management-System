package com.ems.demo.controllerTest;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ems.demo.controllers.DepartmentsController;
import com.ems.demo.models.Department;
import com.ems.demo.models.DepartmentDto;
import com.ems.demo.services.DepartmentsRepository;

@ExtendWith(MockitoExtension.class)
public class DepartmentsControllerTest {

	@Mock
	private DepartmentsRepository departmentsRepository;

	@InjectMocks
	private DepartmentsController departmentsController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(departmentsController).build();
	}

	@Test
	public void testShowDepartmentList() throws Exception {
		
		List<Department> departments = new ArrayList<>();
		departments.add(new Department(1L, "Department 1"));
		departments.add(new Department(2L, "Department 2"));
		when(departmentsRepository.findAll()).thenReturn(departments);

		
		mockMvc.perform(get("/departments")).andExpect(status().isOk()).andExpect(view().name("departments/index"))
				.andExpect(model().attribute("departments", hasSize(2)));
	}

	@Test
	public void testShowCreatePage() throws Exception {
		
		mockMvc.perform(get("/departments/create")).andExpect(status().isOk())
				.andExpect(view().name("departments/createDepartment"))
				.andExpect(model().attributeExists("departmentDto"));
	}


	@Test
	public void testUpdateDepartment() throws Exception {
		// Given
		long id = 1L;
		Department department = new Department(id, "Department 1");
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("Updated Department");
		when(departmentsRepository.findById(id)).thenReturn(Optional.of(department));


		mockMvc.perform(
				post("/departments/edit").param("id", String.valueOf(id)).flashAttr("departmentDto", departmentDto))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/departments"));

		verify(departmentsRepository, times(1)).findById(id);
		verify(departmentsRepository, times(1)).save(department);
	}

	@Test
	public void testDeleteDepartment() throws Exception {
		long id = 1L;
		Department department = new Department(id, "Department 1");
		when(departmentsRepository.findById(id)).thenReturn(Optional.of(department));

		mockMvc.perform(get("/departments/delete").param("id", String.valueOf(id)))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/departments"));

		verify(departmentsRepository, times(1)).findById(id);
		verify(departmentsRepository, times(1)).delete(department);
	}

}
