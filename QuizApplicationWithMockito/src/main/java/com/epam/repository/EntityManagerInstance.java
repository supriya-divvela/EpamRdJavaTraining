package com.epam.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerInstance {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("my-local-quiz-application");
	private static EntityManager entityManager;

	private EntityManagerInstance() {
	}

	public static EntityManager getEntityManagerInstance() {
		if (entityManager == null) {
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

}
