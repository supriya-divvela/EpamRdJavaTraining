package com.epam.testdao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuizDaoImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuizDao {
	@InjectMocks
	private QuizDaoImplementation quizDao;
	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<Quiz> query;
	@Mock
	private TypedQuery<Long> count;
	@Mock
	private EntityTransaction transaction;
	private List<Quiz> quizsList;

	@BeforeEach
	public void createQuizs() {
		quizsList = new ArrayList<>();
		Quiz quiz1 = new Quiz();
		Quiz quiz2 = new Quiz();
		Question question = new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		quiz1.setQuizNo(1);
		quiz1.setTitle("Java Quiz");
		quiz1.getListOfQuestions().add(question);
		quiz1.setListOfQuestions(quiz1.getListOfQuestions());
		quizsList.add(quiz1);
		quiz2.setQuizNo(1);
		quiz2.setTitle("Java Quiz");
		quiz2.getListOfQuestions().add(question);
		quiz1.setListOfQuestions(quiz2.getListOfQuestions());
		quizsList.add(quiz2);
	}

	@Test
	void testGetAllQuestions() {
		when(entityManager.createQuery("from Quiz", Quiz.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(quizsList);
		assertEquals(2,quizDao.getQuizsList().size());
	}

	@Test
	void testAddQuiz() throws DuplicateQuizNumberException {
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class)).thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		when(entityManager.getTransaction()).thenReturn(transaction);
		Quiz quiz=new Quiz();
		quiz.setQuizNo(2);
		doNothing().when(entityManager).persist(quiz);
		assertEquals(2,quizDao.addQuiz(quiz));
		verify(transaction,times(1)).begin();
		verify(transaction,times(1)).commit();
	}

	@Test
	void testAddDuplicateQuiz() {
		Quiz quiz = new Quiz();
		quiz.setQuizNo(2);
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class))
				.thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		assertThrows(DuplicateQuizNumberException.class, () -> quizDao.addQuiz(quiz));
	}

	@Test
	void testRemoveExistingQuiz() throws QuizNotFoundException {
		Quiz quiz=new Quiz();
		quiz.setQuizNo(2);
		when(entityManager.createQuery("select q from Quiz q where q.quizNo = :quizNo", Quiz.class)
				).thenReturn(query);
		when(query.setParameter("quizNo",2)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(quiz);
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class)).thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).remove(quiz);
		assertEquals(2,quizDao.removeQuiz(2));
		verify(transaction,times(1)).begin();
		verify(transaction,times(1)).commit();
	}

	@Test
	void testRemoveQuizNotPresentInLibrary() {
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class)).thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		assertThrows(QuizNotFoundException.class, () -> quizDao.removeQuiz(2));
	}
	@Test
	void testGetQuizPresentInLibrary() throws QuizNotFoundException {
		Quiz quiz=new Quiz();
		quiz.setQuizNo(2);
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class)).thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		when(entityManager.createQuery("select q from Quiz q where q.quizNo = :quizNo", Quiz.class)
				).thenReturn(query);
		when(query.setParameter("quizNo",2)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(quiz);
		assertEquals(quiz.toString(),quizDao.getQuiz(2).toString());
	}
	
	@Test
	void testGetQuizNotPresentInLibrary() throws QuizNotFoundException {
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class)).thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		assertThrows(QuizNotFoundException.class, () -> quizDao.getQuiz(2));
	}
	@Test
	void testFindQuizPresentInLibrary() {
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class)).thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		assertTrue(quizDao.findQuiz(2));
	}
	
	@Test
	void testFindQuizNotPresentInLibrary() {
		when(entityManager.createQuery("select COUNT(*) from Quiz q where q.quizNo ='" + 2 + "'", Long.class)).thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		assertFalse(quizDao.findQuiz(2));
	}
}
