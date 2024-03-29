package com.ems.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ems.demo.models.ApplicationUser;
import com.ems.demo.services.UserRepository;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder password) {
		return args-> {
			
			if(userRepository.findByusername("User").isPresent()) return;
			ApplicationUser user = new ApplicationUser((long)1,"User", password.encode("password"));
			userRepository.save(user);
		};
	}

}
