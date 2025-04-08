package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.epam.model.Student;
import com.epam.service.StudentServiceImpl;

@SpringBootApplication
public class SpringBootDemo2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootDemo2Application.class,
				args);
		Student s = new Student(1, "helo");
		StudentServiceImpl studentServiceImpl = applicationContext.getBean(StudentServiceImpl.class);
		System.out.println(studentServiceImpl.addStudent(s));
		System.out.println(studentServiceImpl.getAllStudents());

	}

}
