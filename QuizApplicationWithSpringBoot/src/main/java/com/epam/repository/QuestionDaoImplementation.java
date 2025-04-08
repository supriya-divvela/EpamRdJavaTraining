package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.model.Question;

@Repository
public class QuestionDaoImplementation implements QuestionDao {
	@Autowired
	private EntityManager entityManager;

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
