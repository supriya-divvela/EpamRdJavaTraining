package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuizDao;
import com.epam.service.QuizServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuizService {
	@Mock
	private QuizDao quizDao;
	@InjectMocks
	private QuizServiceImplementation quizService;
	private List<Quiz> quizs;

	@BeforeEach
	public void createQuizs() {
		quizs = new ArrayList<>();
		Quiz quiz1 = new Quiz();
		Question question = new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		quiz1.setQuizNo(1);
		quiz1.setTitle("Java Quiz");
		quiz1.getListOfQuestions().add(question);
		quiz1.setListOfQuestions(quiz1.getListOfQuestions());
		quizs.add(quiz1);
		Quiz quiz2 = new Quiz();
		quiz2.setQuizNo(2);
		quiz2.setTitle("Java Quiz");
		quiz2.getListOfQuestions().add(question);
		quiz2.setListOfQuestions(quiz2.getListOfQuestions());
		quizs.add(quiz2);
	}

	@Test
	void testAddExistingQuiz() {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		Quiz quiz=new Quiz();
		quiz.setQuizNo(1);
		assertThrows(DuplicateQuizNumberException.class,()->quizService.addQuiz(quiz));
	}

	@Test
	void testAddNewQuiz() throws DuplicateQuizNumberException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		Quiz quiz=new Quiz();
		quiz.setQuizNo(34);
		doNothing().when(quizDao).addQuiz(quiz);
		quizService.addQuiz(quiz);
		assertTrue(quizDao.getQuizsList().size()>0);
	}

	@Test
	void testRemoveExistingQuiz() throws QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		Quiz quiz=new Quiz();
		quiz.setQuizNo(1);
		doNothing().when(quizDao).removeQuiz(quiz);
		quizService.removeQuiz(quiz);
		verify(quizDao).removeQuiz(quiz);
	}

	@Test
	void testRemoveQuizNotPresentInQuizLibrary() throws QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		Quiz quiz=new Quiz();
		quiz.setQuizNo(1234);
		assertThrows(QuizNotFoundException.class,()->quizService.removeQuiz(quiz));
	}

	@Test
	void testNonEmptyQuizLibrary() {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertFalse(quizService.isEmptyQuizLibrary());
	}

	@Test
	void testEmptyQuizLibrary() {
		quizs.clear();
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertTrue(quizService.isEmptyQuizLibrary());
	}

	@Test
	void testFindQuizPresentInLibrary() {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertTrue(quizService.findQuiz(1));
	}

	@Test
	void testFindQuizNotPresentInLibrary() {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertFalse(quizService.findQuiz(12345));
	}

	@Test
	void testDisplayAllQuizes() {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertEquals("\nQuiz [quizNo=1, title=Java Quiz, listOfQuestions=[Question [qNo=1, title=What is java ?, options=[object oriented, platform independent, not secure, has pointers concept], difficulty=easy, taggingTopic=java, answers=[3, 4], marks=5]]]\n"
				+ "Quiz [quizNo=2, title=Java Quiz, listOfQuestions=[Question [qNo=1, title=What is java ?, options=[object oriented, platform independent, not secure, has pointers concept], difficulty=easy, taggingTopic=java, answers=[3, 4], marks=5]]]\n"
				+ "",quizService.displayAvaliableQuizes());
	}

	@Test
	void testDisplayAllQuizesInEmptyQuizLibrary() {
		assertEquals("\n", quizService.displayAvaliableQuizes());
	}

	@Test
	void testGetQuizNotPresentInQuizLibrary() {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertThrows(QuizNotFoundException.class,()->quizService.getQuiz(1234));
	}

	@Test
	void testGetQuizInQuizLibrary() throws QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertTrue(quizService.getQuiz(1).getListOfQuestions().size()>0);
	}

	@Test
	void testGetQuizPresentInQuizLibrary() throws QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		Quiz quiz = new Quiz();
		Question question = new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		quiz.setQuizNo(1);
		quiz.setTitle("Java Quiz");
		quiz.getListOfQuestions().add(question);
		quiz.setListOfQuestions(quiz.getListOfQuestions());
		assertEquals(quiz,quizService.getQuiz(1));
	}

	@Test
	void testaddNewQuestionAddingToQuiz() throws QuestionNotFoundException, QuizNotFoundException, DuplicateQuestionNumberException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertTrue(quizService.addQuestionToQuiz(quizService.getQuiz(1),new Question(12, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));

	}

	@Test
	void testaddExistingQuestionAddingToQuiz() throws DuplicateQuestionNumberException, QuestionNotFoundException, QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertThrows(DuplicateQuestionNumberException.class,()->quizService.addQuestionToQuiz(quizService.getQuiz(1),new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
	}

	@Test
	void testRemovingExistingQuestionInQuiz() throws QuestionNotFoundException, QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertTrue(quizService.removeQuestionFromQuiz(quizService.getQuiz(1),new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
	}

	@Test
	void testRemovingQuestionNotPresentInQuiz() throws QuestionNotFoundException, QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertFalse(quizService.removeQuestionFromQuiz(quizService.getQuiz(1),new Question(1876, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
	}

	@Test
	void testQuestionPresentInQuiz() throws QuestionNotFoundException, QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertTrue(quizService.isQuestionPresentInQuiz(quizService.getQuiz(1),1));
	}

	@Test
	void testQuestionNotPresentInQuiz() throws QuestionNotFoundException, QuizNotFoundException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertFalse(quizService.isQuestionPresentInQuiz(quizService.getQuiz(1),1234));
	}
}
