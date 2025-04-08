package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuizNotFoundException;
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
	public int addQuiz(Quiz quiz) throws DuplicateQuizNumberException {
		if (findQuiz(quiz.getQuizNo())) {
			throw new DuplicateQuizNumberException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(quiz);
		entityManager.getTransaction().commit();
		return quiz.getQuizNo();
	}

	@Override
	public int removeQuiz(int quizNo) throws QuizNotFoundException {
		if (findQuiz(quizNo)) {
			throw new QuizNotFoundException();
		}
		entityManager.getTransaction().begin();
		entityManager.remove(getQuiz(quizNo));
		entityManager.getTransaction().commit();
		return quizNo;
	}

	@Override
	public int updateQuiz(int quizNo) throws QuizNotFoundException {
		if (!findQuiz(quizNo)) {
			throw new QuizNotFoundException();
		}
		entityManager.getTransaction().begin();
		entityManager.merge(getQuiz(quizNo));
		entityManager.getTransaction().commit();
		return quizNo;
	}

	@Override
	public Quiz getQuiz(int quizNo) throws QuizNotFoundException {
		if (!findQuiz(quizNo)) {
			throw new QuizNotFoundException();
		}
		return entityManager.createQuery("select q from Quiz q where q.quizNo = :quizNo", Quiz.class)
				.setParameter("quizNo", quizNo).getSingleResult();
	}

	@Override
	public boolean findQuiz(int quizNo) {
		return entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + quizNo + "'", Long.class)
				.getSingleResult() == 1;
	}
}
