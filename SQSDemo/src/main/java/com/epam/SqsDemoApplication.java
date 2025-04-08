package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@EnableSqs
@SpringBootApplication//(exclude= {ContextStackAutoConfiguration.class})
public class SqsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsDemoApplication.class, args);
	}
	
}
