package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.epam.dto.QuizDto;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.EmptyQuizLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuizDao;
import com.epam.service.QuestionService;
import com.epam.service.QuizServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuizService {
	@Mock
	private QuizDao quizDao;
	@InjectMocks
	private QuizServiceImplementation quizService;
	@Mock
	private QuestionService questionService;
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
	void testAddNewQuiz() throws DuplicateQuizNumberException {
		QuizDto quizDto = new QuizDto();
		quizDto.setQuizNo(12345);
		when(quizService.addQuiz(quizDto)).thenReturn(12345);
		assertEquals(12345, quizService.addQuiz(quizDto));
	}

	@Test
	void testAddExistingQuiz() throws DuplicateQuizNumberException {
		QuizDto quizDto = new QuizDto();
		when(quizService.addQuiz(quizDto)).thenThrow(DuplicateQuizNumberException.class);
		assertThrows(DuplicateQuizNumberException.class, () -> quizService.addQuiz(quizDto));
	}

	@Test
	void testRemoveExistingQuiz() throws QuizNotFoundException {
		when(quizDao.removeQuiz(1)).thenReturn(1);
		assertEquals(1,quizService.removeQuiz(1));
	}

	@Test
	void testRemoveQuizNotPresentInQuizLibrary() throws QuizNotFoundException {
		when(quizDao.removeQuiz(1234)).thenThrow(QuizNotFoundException.class);
		assertThrows(QuizNotFoundException.class,()->quizService.removeQuiz(1234));
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
		when(quizDao.findQuiz(1)).thenReturn(true);
		assertTrue(quizService.findQuiz(1));
	}

	@Test
	void testFindQuizNotPresentInLibrary() {
		when(quizDao.findQuiz(12345)).thenReturn(false);
		assertFalse(quizService.findQuiz(12345));
	}

	@Test
	void testDisplayAllQuizes() throws EmptyQuizLibraryException {
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertEquals(quizs.size(),quizService.displayAvaliableQuizes().size());
	}

	@Test
	void testDisplayAllQuizesInEmptyQuizLibrary() {
		quizs.clear();
		when(quizDao.getQuizsList()).thenReturn(quizs);
		assertThrows(EmptyQuizLibraryException.class, () -> quizService.displayAvaliableQuizes());
	}

	@Test
	void testGetQuizNotPresentInQuizLibrary() throws QuizNotFoundException {
		when(quizDao.getQuiz(1234)).thenThrow(QuizNotFoundException.class);
		assertThrows(QuizNotFoundException.class,()->quizService.getQuiz(1234));
	}

	@Test
	void testGetQuizInQuizLibrary() throws QuizNotFoundException {
		Quiz quiz = new Quiz();
		when(quizDao.getQuiz(1)).thenReturn(quiz);
		assertEquals(quiz.toString(), quizService.getQuiz(1).toString());
	}

	@Test
	void testUpdateQuiz() throws QuizNotFoundException {
		Quiz quiz = quizs.get(1);
		QuizDto quizDto = new QuizDto();
		Question question = new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		quizDto.setQuizNo(2);
		quizDto.setTitle("python Quiz");
		quizDto.setListOfQuestions(Arrays.asList(question));
		when(quizService.getQuiz(1)).thenReturn(quiz);
		quizService.updateQuiz(1, quizDto);
		assertEquals(quizService.getQuiz(1).toString(), quizDto.toString());
	}

	@Test
	void testCreateQuiz()
			throws QuestionNotFoundException, DuplicateQuizNumberException, EmptyQuestionLibraryException {
		QuizDto quiz = new QuizDto();
		quiz.setQuizNo(1);
		quiz.setTitle("hii");
		Question question = new Question();
		question.setQNo(1);
		quiz.setListOfQuestions(Arrays.asList(question));
		List<Question> questionsList = new ArrayList<>();
		questionsList.add(question);
		when(questionService.findQuestion(1)).thenReturn(true);
		when(questionService.getQuestion(1)).thenReturn(question);
		assertEquals(1, quizService.createQuiz(1, "hii", new ArrayList<>(Arrays.asList(1))).getQuizNo());
	}
}
