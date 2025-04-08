package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.epam.model.Question;

public class QuestionDaoImplementation implements QuestionDao {
	private EntityManager entityManager = EntityManagerInstance.getEntityManagerInstance();

	@Override
	public List<Question> getQuestionsList() {
		return entityManager.createQuery("from Question", Question.class).getResultList();
	}

	@Override
	public void addQuestion(Question question) {
		if (question == null) {
			throw new NullPointerException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(question);
		entityManager.getTransaction().commit();
	}

	@Override
	public void removeQuestion(Question question) {
		if (question == null) {
			throw new NullPointerException();
		}
		entityManager.getTransaction().begin();
		entityManager.remove(question);
		entityManager.getTransaction().commit();
	}

	@Override
	public void updateQuestion(Question question) {
		if (question == null) {
			throw new NullPointerException();
		}
		entityManager.getTransaction().begin();
		entityManager.merge(question);
		entityManager.getTransaction().commit();

	}

}
