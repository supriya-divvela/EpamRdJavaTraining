package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class LibraryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
