package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class QuizApplicationWithSpringDataJpa {
	public static void main(String[] args) {
		SpringApplication.run(QuizApplicationWithSpringDataJpa.class, args);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		return new LocalSessionFactoryBean();
	}
}