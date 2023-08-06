package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.epam.model.Quiz;

public class QuizDaoImplementation implements QuizDao {
	private EntityManager entityManager = EntityManagerInstance.getEntityManagerInstance();

	@Override
	public List<Quiz> getQuizsList() {
		return entityManager.createQuery("from Quiz", Quiz.class).getResultList();
	}

	@Override
	public void addQuiz(Quiz quiz) {
		if (quiz == null) {
			throw new NullPointerException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(quiz);
		entityManager.getTransaction().commit();
	}

	@Override
	public void removeQuiz(Quiz quiz) {
		if (quiz == null) {
			throw new NullPointerException();
		}
		entityManager.getTransaction().begin();
		entityManager.remove(quiz);
		entityManager.getTransaction().commit();
	}
}
