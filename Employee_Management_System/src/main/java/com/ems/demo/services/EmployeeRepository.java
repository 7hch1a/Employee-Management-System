package com.ems.demo.services;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.demo.models.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

	List<Employee> findAll();
}
