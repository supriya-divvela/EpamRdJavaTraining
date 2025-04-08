package com.epam.mockito.testdao;

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
		assertTrue(questionDao.getQuestionsList().size()>0);
	}
//	@Override
//	public void addQuestion(Question question) {
//		if (question == null) {
//			throw new NullPointerException();
//		}
//		entityManager.getTransaction().begin();
//		entityManager.persist(question);
//		entityManager.getTransaction().commit();
//	}

	@Test
	void testAddQuestion() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		Question question=new Question(4, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		doNothing().when(entityManager).persist(question);
		questionDao.addQuestion(question);
		verify(transaction).begin();
		verify(transaction,times(1)).commit();
	}

	@Test
	void addNullQuestion() {
		assertThrows(NullPointerException.class, () -> questionDao.addQuestion(null));
	}

	@Test
	void testRemoveQuestion() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		Question question=new Question(4, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		doNothing().when(entityManager).remove(question);
		questionDao.removeQuestion(question);
		verify(transaction).begin();
		verify(transaction,times(1)).commit();
	}

	@Test
	void testRemoveNullQuestion() {
		assertThrows(NullPointerException.class, () -> questionDao.removeQuestion(null));
	}
	
	@Test
	void testUpdateQuestion() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		Question question=new Question();
		question.setQNo(35);
		when(entityManager.merge(question)).thenReturn(question);
		questionDao.updateQuestion(question);
		verify(transaction).begin();
		verify(transaction,times(1)).commit();
	}

	@Test
	void testUpdateNullQuestion() {
		assertThrows(NullPointerException.class, () -> questionDao.updateQuestion(null));
	}
}
