package com.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.epam.filter.JwtAuthFilter;
import com.epam.service.CustomUserDetailsService;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

	 @Autowired
	 private JwtAuthFilter authFilter;
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
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/admin","/hello","/token").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/welcome", "/user").authenticated().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().
				authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
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
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(getUserDetails());
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;

	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
