package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.AuthRequest;
import com.epam.model.Student;
import com.epam.repository.StudentRepository;
import com.epam.service.JwtService;

@RestController
public class DemoController {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationProvider authenticationProvider;

	
//	@PreAuthorize("hasAuthority('ROLE_admin') or hasAuthority('ROLE_user')")
	@GetMapping("/welcome")
	public String getWelcome() {
		return "welcome message for both admin and user";
	}

	@PostMapping("/admin")
	public Student getAdmin(@org.springframework.web.bind.annotation.RequestBody Student student) {
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		return studentRepository.save(student);
	}
	
	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}
	
//	@PreAuthorize("hasAuthority('ROLE_user')")
	@GetMapping("/user")
	public List<Student> getAllUser() {
		return studentRepository.findAll();
	}
	
	@PostMapping("/token")
	public String getToken(@org.springframework.web.bind.annotation.RequestBody AuthRequest authrequest) {
		org.springframework.security.core.Authentication authentication=authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(authrequest.getUsername());
		}
		else {
			throw new UsernameNotFoundException("User not found..");
		}
	}
}
