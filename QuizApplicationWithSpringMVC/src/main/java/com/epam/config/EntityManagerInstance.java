package com.epam.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;

public class EntityManagerInstance {
	@Bean
	public EntityManager getEntityManagerInstance() {
		return Persistence.createEntityManagerFactory("my-local-quiz-application").createEntityManager();

	}
}
