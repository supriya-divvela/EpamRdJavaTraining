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

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.repository.QuestionDaoImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuestionDao {
	@Mock
	private EntityManager entityManager;
	@InjectMocks
	private QuestionDaoImplementation questionDao;
	@Mock
	private TypedQuery<Question> query;
	@Mock
	private TypedQuery<Long> count;
	@Mock
	private EntityTransaction transaction;
	private List<Question> questionsList;

	@BeforeEach
	public void createQuestions() {
		questionsList = new ArrayList<>(Arrays.asList(
				new Question(1, "What is java ?",
						new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
								"has pointers concept")),
						"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5),
				new Question(2, "What is java ?",
						new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
								"has pointers concept")),
						"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5),
				new Question(3, "What is java ?",
						new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
								"has pointers concept")),
						"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));

	}

	@Test
	void testGetAllQuestions() {
		when(entityManager.createQuery("from Question",Question.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(questionsList);
		assertEquals(3,questionDao.getQuestionsList().size());
	}

	@Test
	void testAddQuestion() throws DuplicateQuestionNumberException {
		Question question = new Question(4, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
				.thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(question);
		questionDao.addQuestion(question);
		verify(transaction, times(1)).begin();
		verify(transaction, times(1)).commit();
	}

	@Test
	void testAddDuplicateQuestion() {
		Question question = new Question();
		question.setQNo(4);
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
				.thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		assertThrows(DuplicateQuestionNumberException.class, () -> questionDao.addQuestion(question));
	}

	@Test
	void testRemoveQuestion() throws QuestionNotFoundException {
		Question question = new Question(4, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
				.thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		when(entityManager.getTransaction()).thenReturn(transaction);
		when(entityManager.createQuery("select q from Question q where q.qNo = :qNo", Question.class))
				.thenReturn(query);
		when(query.setParameter("qNo", 4)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(question);
		when(questionDao.getQuestion(4)).thenReturn(question);
		doNothing().when(entityManager).remove(question);
		assertEquals(4, questionDao.removeQuestion(4));
		verify(transaction, times(1)).begin();
		verify(transaction, times(1)).commit();
	}

	@Test
	void testRemoveQuestionNotPresentInLibrary() {
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
				.thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		assertThrows(QuestionNotFoundException.class, () -> questionDao.removeQuestion(4));
	}

	@Test
	void testGetQuestionPresentInLibrary() throws QuestionNotFoundException {
		Question question = new Question(4, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
				.thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		when(entityManager.createQuery("select q from Question q where q.qNo = :qNo", Question.class))
				.thenReturn(query);
		when(query.setParameter("qNo", 4)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(question);
		assertEquals(question.toString(), questionDao.getQuestion(4).toString());

	}

	@Test
	void testGetQuestionNotPresentInLibrary() {
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
		.thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		assertThrows(QuestionNotFoundException.class,()->questionDao.getQuestion(4));
	}
	@Test
	void testFindQuestionPresentInLibrary() {
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
		.thenReturn(count);
		when(count.getSingleResult()).thenReturn(1L);
		assertTrue(questionDao.findQuestion(4));
	}
	
	@Test
	void testFindQuestionNotPresentInLibrary() {
		when(entityManager.createQuery("select COUNT(*) from Question q where q.qNo ='" + 4 + "'", Long.class))
		.thenReturn(count);
		when(count.getSingleResult()).thenReturn(0L);
		assertFalse(questionDao.findQuestion(4));
	}
}
