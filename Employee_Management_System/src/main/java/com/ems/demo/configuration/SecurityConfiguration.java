package com.ems.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authManager(UserDetailsService detailsService) {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(detailsService);
		return new ProviderManager(daoProvider);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                	auth.requestMatchers("/api/**").authenticated();
                	auth.anyRequest().authenticated();
                })
                .logout(logout -> logout.logoutUrl("/api/logout")) //not run
                .httpBasic(withDefaults())
                .build();
	}
}
