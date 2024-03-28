package com.ems.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.demo.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	List<Project> findAll();

}
