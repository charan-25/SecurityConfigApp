package com.spring.security.config.app;

import com.spring.security.config.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
public class SpringSecurityConfigAppApplication {

	@Autowired
	private UserRepo userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityConfigAppApplication.class, args);
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
