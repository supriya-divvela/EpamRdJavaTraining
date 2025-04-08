package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.epam.config.EntityManagerInstance;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@Import({ EntityManagerInstance.class })
public class QuizApplicationWithSpringMVCModify {
	public static void main(String[] args) {
		 SpringApplication.run(QuizApplicationWithSpringMVCModify.class, args);
	
	}
}