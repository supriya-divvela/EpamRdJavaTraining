package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.QuestionNotFoundException;
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
	public int addQuestion(Question question) throws DuplicateQuestionNumberException {
		if (findQuestion(question.getQNo())) {
			throw new DuplicateQuestionNumberException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(question);
		entityManager.getTransaction().commit();
		return question.getQNo();
	}

	@Override
	public int updateQuestion(int qNo) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		entityManager.getTransaction().begin();
		entityManager.merge(getQuestion(qNo));
		entityManager.getTransaction().commit();
		return qNo;
	}

	@Override
	public Question getQuestion(int qNo) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		return entityManager.createQuery("select q from Question q where q.qNo = :qNo", Question.class)
				.setParameter("qNo", qNo).getSingleResult();
	}

	@Override
	public int removeQuestion(int qNo) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		entityManager.getTransaction().begin();
		entityManager.remove(getQuestion(qNo));
		entityManager.getTransaction().commit();
		return qNo;
	}

	@Override
	public boolean findQuestion(int qNo) {
		return entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + qNo + "'", Long.class)
				.getSingleResult() == 1;
	}

}
