package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.epam.service.CustomUserDetailsService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.0"), security = {
		@SecurityRequirement(name = "basicAuth") })
@SecuritySchemes({ @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic") })
@EnableMethodSecurity
@Configuration
public class QuizApplicationWithSpringSecurity {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
//	public QuizApplicationWithSpringSecurity(CustomUserDetailsService customUserDetailsService) {
//		this.customUserDetailsService=customUserDetailsService;
//	}
	public static final String[] PUBLIC_PATHS = { "/v3/api-docs.yaml", "/v3/api-docs/**", "/swagger-ui/**",
			"/swagger-ui.html" };

	public static void main(String[] args) {
		SpringApplication.run(QuizApplicationWithSpringSecurity.class, args);
	}

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder) {
//		return args -> {
//			users.save(new User("user@epam.com", encoder.encode("User@123"), "ROLE_USER"));
//			users.save(new User("admin@epam.com", encoder.encode("Admin@123"), "ROLE_USER,ROLE_ADMIN"));
//		};
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//
//	@Bean
//	public CustomUserDetailsService customUserDetailsService() {
//		return new CustomUserDetailsService();
//
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(PUBLIC_PATHS).permitAll().anyRequest().authenticated())
				.userDetailsService(customUserDetailsService).httpBasic(Customizer.withDefaults()).build();
	}
}
