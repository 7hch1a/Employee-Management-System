package com.ems.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ems.demo.models.Department;

public interface DepartmentsRepository extends JpaRepository<Department, Long> {

	List<Department> findAll();

}
