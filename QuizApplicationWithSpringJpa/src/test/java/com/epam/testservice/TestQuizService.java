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
import java.util.Optional;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.QuizDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.jparepository.QuestionRepository;
import com.epam.jparepository.QuizRepository;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuestionServiceJpaImplementation;
import com.epam.service.QuizServiceJpaImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuizService {
	@Mock
	private QuizRepository quizRepository;
	@Mock
	private QuestionRepository questionRepository;
	@InjectMocks
	private QuizServiceJpaImplementation quizService;
	@Mock
	private QuestionServiceJpaImplementation questionService;
	@Mock
	private ModelMapper modelMapper;
	private List<Quiz> quizs;

	@BeforeEach
	public void createQuizs() {
		quizs = new ArrayList<>();
		Quiz quiz1 = new Quiz();
		Question question = new Question( "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		question.setId(1);
		quiz1.setId(1);
		quiz1.setTitle("Java Quiz");
		quiz1.getListOfQuestions().add(question);
		quiz1.setListOfQuestions(quiz1.getListOfQuestions());
		quizs.add(quiz1);
		Quiz quiz2 = new Quiz();
		quiz2.setId(2);
		quiz2.setTitle("Java Quiz");
		quiz2.getListOfQuestions().add(question);
		quiz2.setListOfQuestions(quiz2.getListOfQuestions());
		quizs.add(quiz2);
	}

	@Test
	void testAddNewQuiz() {
		QuizDto quizDto = new QuizDto();
		quizDto.setId(12345);
		Quiz quiz = new Quiz();
		quiz.setId(12345);
		when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
		when(quizRepository.save(Mockito.any(Quiz.class))).thenReturn(quiz);
		assertEquals(12345, quizService.addQuiz(quizDto));
	}

	@Test
	void testRemoveExistingQuiz() throws QuizNotFoundException {
		when(quizRepository.existsById(1234)).thenReturn(true);
		doNothing().when(quizRepository).deleteById(1234);
		quizService.removeQuiz(1234);
		verify(quizRepository).deleteById(1234);
	}

	@Test
	void testRemoveQuizNotPresentInQuizLibrary() throws QuizNotFoundException {
		when(quizRepository.existsById(1234)).thenReturn(false);
		assertThrows(QuizNotFoundException.class,()->quizService.removeQuiz(1234));
	}

	@Test
	void testNonEmptyQuizLibrary() {
		when(quizRepository.count()).thenReturn(2L);
		assertFalse(quizService.isEmptyQuizLibrary());
	}

	@Test
	void testEmptyQuizLibrary() {
		when(quizRepository.count()).thenReturn(0L);
		assertTrue(quizService.isEmptyQuizLibrary());
	}

	@Test
	void testFindQuizPresentInLibrary() {
		when(quizRepository.existsById(1)).thenReturn(true);
		assertTrue(quizService.findQuiz(1));
	}

	@Test
	void testFindQuizNotPresentInLibrary() {
		when(quizRepository.existsById(12345)).thenReturn(false);
		assertFalse(quizService.findQuiz(12345));
	}

	@Test
	void testDisplayAllQuizes() {
		when(quizRepository.findAll()).thenReturn(quizs);
		assertEquals(quizs.size(),quizService.displayAvaliableQuizes().size());
	}

	@Test
	void testGetQuizNotPresentInQuizLibrary() throws QuizNotFoundException {
		when(quizRepository.findById(1234)).thenReturn(Optional.empty());
		assertThrows(QuizNotFoundException.class,()->quizService.getQuiz(1234));
	}

	@Test
	void testGetQuizInQuizLibrary() throws QuizNotFoundException {
	    Quiz quiz = new Quiz();
		when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
		assertEquals(quiz.toString(), quizService.getQuiz(1).toString());
	}

	@Test
	void testUpdateQuiz() throws QuizNotFoundException {
		Quiz quiz = quizs.get(1);
		QuizDto quizDto = new QuizDto();
		Question question = new Question();
		question.setId(1);
		quizDto.setId(2);
		quizDto.setTitle("python Quiz");
		quizDto.setListOfQuestions(Arrays.asList(question));
		when(modelMapper.map(quizDto,Quiz.class)).thenReturn(quiz);
		when(quizRepository.save(Mockito.any(Quiz.class))).thenReturn(quiz);
		quizService.updateQuiz(1, quizDto);
		verify(quizRepository).save(quiz);
	}

	@Test
	void testCreateQuiz()
			throws QuestionNotFoundException {
		QuizDto quiz = new QuizDto();
		quiz.setId(1);
		quiz.setTitle("hii");
		Question question = new Question();
		question.setId(1);
		quiz.setListOfQuestions(Arrays.asList(question));
		List<Question> questionsList = new ArrayList<>();
		questionsList.add(question);
		when(questionRepository.findAllById(Mockito.any())).thenReturn(questionsList);
		assertEquals("hii", quizService.createQuiz("hii", new ArrayList<>(Arrays.asList(1))).getTitle());
	}
}
