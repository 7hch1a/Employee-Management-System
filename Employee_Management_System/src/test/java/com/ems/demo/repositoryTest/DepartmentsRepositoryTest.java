package com.ems.demo.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ems.demo.models.Department;
import com.ems.demo.services.DepartmentsRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations="classpath:application.properties")
public class DepartmentsRepositoryTest {

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Test
    public void testFindAll() {
        // Given
        Department department1 = new Department(1L, "Human Resources");
        Department department2 = new Department(2L, "Finance");
        departmentsRepository.save(department1);
        departmentsRepository.save(department2);

        // When
        List<Department> departments = departmentsRepository.findAll();

        //
        assertThat(departments).isNotNull();

        //Check Human Resource
        assertThat(departments)
        .filteredOn(department -> department.getDepartmentName().startsWith("Human Resources"))
        .hasSize(1); 
        
    }

}