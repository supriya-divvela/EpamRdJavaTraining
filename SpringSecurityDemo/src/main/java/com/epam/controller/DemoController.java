package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.Student;
import com.epam.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class DemoController {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
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
	
	
}
