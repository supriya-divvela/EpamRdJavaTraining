package com.epam.testdao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
	private EntityTransaction transaction;
	private List<Quiz> quizsList;

	@BeforeEach
	public void createQuizs() {
		quizsList = new ArrayList<>();
		Quiz quiz = new Quiz();
		Question question = new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		quiz.setQuizNo(1);
		quiz.setTitle("Java Quiz");
		quiz.getListOfQuestions().add(question);
		quiz.setListOfQuestions(quiz.getListOfQuestions());
		quizsList.add(quiz);
		}

	@Test
	void testGetAllQuestions() {
		when(entityManager.createQuery("from Quiz", Quiz.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(quizsList);
		assertTrue(!quizDao.getQuizsList().isEmpty());
	}

	@Test
	void testAddQuiz() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		Quiz quiz=new Quiz();
		quiz.setQuizNo(2);
		doNothing().when(entityManager).persist(quiz);
		quizDao.addQuiz(quiz);
		verify(transaction).begin();
		verify(transaction,times(1)).commit();
	}

	@Test
	void testAddQuizWithException() {
		assertThrows(NullPointerException.class,()->quizDao.addQuiz(null));
	}

	@Test
	void testRemoveQuiz() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		Quiz quiz=new Quiz();
		doNothing().when(entityManager).remove(quiz);
		quizDao.removeQuiz(quiz);
		verify(transaction).begin();
		verify(transaction,times(1)).commit();
	}
	
	@Test
	void testRemoveNullQuiz() {
		assertThrows(NullPointerException.class,()->quizDao.removeQuiz(null));	
	}
}
