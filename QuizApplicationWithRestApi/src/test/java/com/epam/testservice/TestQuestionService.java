package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.jparepository.QuestionRepository;
import com.epam.model.Question;
import com.epam.service.QuestionServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestQuestionService {
	@Mock
	private QuestionRepository questionRepository;
	@InjectMocks
	private QuestionServiceImpl questionService;
	@Mock
	private ModelMapper modelMapper;

	private List<Question> questionsList;

	@Test
	void testAddingNewQuestion() throws QuestionException {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(4);
		Question question = new Question();
		question.setId(4);
		questionDto.setOptions(List.of("1","2"));
		questionDto.setAnswers(Set.of(1));
		when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
		when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
		when(questionRepository.save(question)).thenReturn(question);
		assertEquals(questionDto, questionService.addQuestion(questionDto));
	}
	
	@Test
	void testAddingNewQuestionWithOptionsException() throws QuestionException {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(4);
		questionDto.setId(4);
		questionDto.setOptions(List.of("1"));
		questionDto.setAnswers(Set.of(5));
		assertThrows(QuestionException.class,()-> questionService.addQuestion(questionDto));
	}

	@Test
	void testUpdateQuestion() throws QuestionException {
		QuestionDto questionDto = new QuestionDto();
		Question question = new Question();
		question.setId(2);
		questionDto.setId(2);
		when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
		when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
		when(questionRepository.save(question)).thenReturn(question);
		when(questionRepository.findById(2)).thenReturn(Optional.of(question));
		assertEquals(questionDto, questionService.updateQuestion(questionDto));
	}

	@Test
	void testUpdateQuestionWithException() throws QuestionException {
		QuestionDto questionDto = new QuestionDto();
		Question question = new Question();
		question.setId(2);
		questionDto.setId(2);
		when(questionRepository.findById(2)).thenReturn(Optional.empty());
		assertThrows(QuestionException.class, () -> questionService.updateQuestion(questionDto));
	}

	@Test
	void testEmptyLibrary() {
		when(questionRepository.count()).thenReturn(0L);
		assertTrue(questionService.isEmptyQuestionLibrary());
	}

	@Test
	void testNotEmptyLibrary() {
		when(questionRepository.count()).thenReturn(2L);
		assertFalse(questionService.isEmptyQuestionLibrary());
	}

	@Test
	void testRemovingQuestionPresentInLibrary() throws QuestionException, SQLIntegrityConstraintViolationException {
		doNothing().when(questionRepository).deleteById(2);
		questionService.removeQuestion(2);
		Mockito.verify(questionRepository).deleteById(2);

	}

	@Test
	void testFindQuestionInLibrary() {
		when(questionRepository.existsById(19876)).thenReturn(true);
		assertTrue(questionService.findQuestion(19876));
	}

	@Test
	void testFindQuestionNotInLibrary() {
		when(questionRepository.existsById(19876)).thenReturn(false);
		assertFalse(questionService.findQuestion(19876));
	}

	@Test
	void testGetQuestion() throws QuestionException {
		Question question = new Question();
		question.setId(1);
		when(questionRepository.findById(1)).thenReturn(Optional.of(question));
		questionService.getQuestion(1);
		verify(questionRepository).findById(1);
	}

	@Test
	void testGetQuestionNotPresentInQuestionLibrary() throws QuestionException {
		when(questionRepository.findById(89754)).thenReturn(Optional.empty());
		assertThrows(QuestionException.class,()->questionService.getQuestion(89754));
	}

	@Test
	void testDisplayQuestions() {
		Question question = new Question("What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		question.setId(1);
		questionsList = new ArrayList<>(Arrays.asList(question));
		when(questionRepository.findAll()).thenReturn(questionsList);
		assertTrue(questionService.displayQuestions().size() > 0);
	}

}
