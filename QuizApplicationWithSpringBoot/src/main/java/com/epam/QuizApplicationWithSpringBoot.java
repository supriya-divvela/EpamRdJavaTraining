package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.epam.config.EntityManagerInstance;
import com.epam.ui.QuizApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
@Import({ EntityManagerInstance.class })
public class QuizApplicationWithSpringBoot {
	public static void main(String[] args) {
		var context = SpringApplication.run(QuizApplicationWithSpringBoot.class, args);
		context.getBean(QuizApplication.class).start();
	}
}