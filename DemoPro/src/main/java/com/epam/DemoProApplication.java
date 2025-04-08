package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoProApplication {

	@GetMapping("/message")
	public String getMessage() {
		return "Hello";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoProApplication.class, args);
	}

}
