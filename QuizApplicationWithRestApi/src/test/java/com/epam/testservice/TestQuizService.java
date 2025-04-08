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

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.exception.QuestionException;
import com.epam.exception.QuizException;
import com.epam.jparepository.QuestionRepository;
import com.epam.jparepository.QuizRepository;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuestionServiceImpl;
import com.epam.service.QuizServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestQuizService {
	@Mock
	private QuizRepository quizRepository;
	@Mock
	private QuestionRepository questionRepository;
	@InjectMocks
	private QuizServiceImpl quizService;
	@Mock
	private QuestionServiceImpl questionService;
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
		List<Question> questions=new ArrayList<>();
		question.setId(1);
		quiz1.setId(1);
		quiz1.setTitle("Java Quiz");
		questions.add(question);
		quiz1.setListOfQuestions(questions);
		quizs.add(quiz1);
		Quiz quiz2 = new Quiz();
		quiz2.setId(2);
		quiz2.setTitle("Java Quiz");
		quiz2.setListOfQuestions(questions);
		quizs.add(quiz2);
	}

	@Test
	void testAddNewQuiz() {
		QuizDto quizDto = new QuizDto();
		quizDto.setId(12345);
		Quiz quiz = new Quiz();
		quiz.setId(12345);
		when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
		when(modelMapper.map(quiz,QuizDto.class)).thenReturn(quizDto);
		when(quizRepository.save(Mockito.any(Quiz.class))).thenReturn(quiz);
		assertEquals(quizDto, quizService.addQuiz(quizDto));
	}

	@Test
	void testRemoveExistingQuiz() throws QuizException {
		doNothing().when(quizRepository).deleteById(2);
		quizService.removeQuiz(2);
		Mockito.verify(quizRepository).deleteById(2);
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
		assertTrue(quizService.existQuiz(1));
	}

	@Test
	void testFindQuizNotPresentInLibrary() {
		when(quizRepository.existsById(12345)).thenReturn(false);
		assertFalse(quizService.existQuiz(12345));
	}

	@Test
	void testDisplayAllQuizes() {
		when(quizRepository.findAll()).thenReturn(quizs);
		assertEquals(quizs.size(),quizService.displayAvaliableQuizes().size());
	}

	@Test
	void testGetQuizNotPresentInQuizLibrary() throws QuizException {
		when(quizRepository.findById(1234)).thenReturn(Optional.empty());
		assertThrows(QuizException.class,()->quizService.getQuiz(1234));
	}

	@Test
	void testGetQuizInQuizLibrary() throws QuizException {
	    Quiz quiz = new Quiz();
	    QuizDto quizDto=new QuizDto();
	    quiz.setId(2);
	    quizDto.setId(2);
		when(modelMapper.map(quiz,QuizDto.class)).thenReturn(quizDto);
		when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
		assertEquals(quizDto.toString(), quizService.getQuiz(1).toString());
	}

	@Test
	void testUpdateQuiz() throws QuizException {
		Quiz quiz = new Quiz();
		QuizDto quizDto = new QuizDto();
		quizDto.setId(2);
		quizDto.setTitle("python Quiz");
		quiz.setId(2);
		quiz.setTitle("python Quiz");
		when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
		when(modelMapper.map(quiz,QuizDto.class)).thenReturn(quizDto);
		when(quizRepository.findById(2)).thenReturn(Optional.of(quiz));
		when(quizRepository.save(quiz)).thenReturn(quiz);
		assertEquals(quizDto,quizService.updateQuiz(quizDto));
		verify(quizRepository).save(quiz);
	}

	@Test
	void testUpdateQuizWithException() throws QuizException {
		Quiz quiz = new Quiz();
		QuizDto quizDto = new QuizDto();
		quizDto.setId(2);
		quizDto.setTitle("python Quiz");
		quiz.setId(2);
		quiz.setTitle("python Quiz");
		when(quizRepository.findById(2)).thenReturn(Optional.empty());
		assertThrows(QuizException.class,()->quizService.updateQuiz(quizDto));
	}
	
	@Test
	void testCreateQuiz()
			throws QuestionException {
		QuizDto quiz = new QuizDto();
		quiz.setId(1);
		quiz.setTitle("hii");
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(1);
		quiz.setListOfQuestions(Arrays.asList(questionDto));
		List<QuestionDto> questionsList = new ArrayList<>();
		questionsList.add(questionDto);
		List<Question> questions=new ArrayList<>();
		Question question = new Question();
		question.setId(1);
		questions.add(question);
		when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
		when(questionRepository.findAllById(List.of(1))).thenReturn(questions);
		assertEquals("hii", quizService.createQuiz("hii", new ArrayList<>(Arrays.asList(1))).getTitle());
	}
	
	@Test
	void testCreateQuizWithException()
			throws QuestionException {
		QuizDto quiz = new QuizDto();
		quiz.setId(1);
		quiz.setTitle("hii");
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(1);
		quiz.setListOfQuestions(Arrays.asList(questionDto));
		List<QuestionDto> questionsList = new ArrayList<>();
		questionsList.add(questionDto);
		List<Question> questions=new ArrayList<>();
		when(questionRepository.findAllById(List.of(1,2,3))).thenReturn(questions);
		assertThrows(QuestionException.class,()-> quizService.createQuiz("hii", new ArrayList<>(Arrays.asList(1,2,3))));
	}
}
