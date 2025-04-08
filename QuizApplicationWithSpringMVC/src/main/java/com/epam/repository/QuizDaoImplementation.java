package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.model.Quiz;

@Repository
public class QuizDaoImplementation implements QuizDao {
	@Autowired
	private EntityManager entityManager;

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
