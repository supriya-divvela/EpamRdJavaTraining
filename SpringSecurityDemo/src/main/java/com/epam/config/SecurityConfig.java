package com.epam.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.epam.model.Student;
import com.epam.repository.StudentRepository;
import com.epam.service.CustomUserDetailsService;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService getUserDetails() {
//		UserDetails user = User.builder().username("supriya").password(encoder.encode("password")).roles("user")
//				.build();
//		UserDetails admin = User.builder().username("pavan").password(encoder.encode("password")).roles("admin")
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
		return new CustomUserDetailsService();

	}

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/admin","/hello").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/welcome", "/user").authenticated().and().httpBasic().and()
				.build();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(StudentRepository studentRepository, PasswordEncoder encoder) {
//		return args -> {
//			studentRepository.save(new Student(1, "user", "supriya", encoder.encode("1234"), "ROLE_USER"));
//			studentRepository.save(new Student(1, "user", "pavan", encoder.encode("1234"), "ROLE_USER"));
//		};
//	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(getUserDetails());
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;

	}
}
