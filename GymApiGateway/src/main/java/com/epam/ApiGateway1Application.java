package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
public class ApiGateway1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateway1Application.class, args);
	}

}
