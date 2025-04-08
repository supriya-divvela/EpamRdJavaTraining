package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.jparepository.QuestionRepository;
import com.epam.model.Question;
import com.epam.service.QuestionServiceJpaImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuestionService {
	@Mock
	private QuestionRepository questionRepository;
	@InjectMocks
	private QuestionServiceJpaImplementation questionServiceJpaImplementation;
	@Mock
	private ModelMapper modelMapper;

	private List<Question> questionsList;

	@Test
	void testAddingNewQuestion() {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(4);
		Question question = new Question();
		question.setId(4);
		when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
		when(questionRepository.save(question)).thenReturn(question);
		assertEquals(4, questionServiceJpaImplementation.addQuestion(questionDto));
	}

	@Test
	void testUpdateQuestion() throws QuestionNotFoundException {
		QuestionDto questionDto = new QuestionDto();
		Question question = new Question();
		question.setId(2);
		when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
		when(questionRepository.save(question)).thenReturn(question);
		questionServiceJpaImplementation.updateQuestion(2, questionDto);
		verify(questionRepository).save(question);
	}

	@Test
	void testEmptyLibrary() {
		when(questionRepository.count()).thenReturn(0L);
		assertTrue(questionServiceJpaImplementation.isEmptyQuestionLibrary());
	}

	@Test
	void testNotEmptyLibrary() {
		when(questionRepository.count()).thenReturn(2L);
		assertFalse(questionServiceJpaImplementation.isEmptyQuestionLibrary());
	}

	@Test
	void testRemovingQuestionNotPresentInQuestionLibrary() throws QuestionNotFoundException {
		when(questionRepository.existsById(1)).thenReturn(false);
		assertThrows(QuestionNotFoundException.class,()-> questionServiceJpaImplementation.removeQuestion(1));
	}

	@Test
	void testRemovingQuestionPresentInLibrary() throws QuestionNotFoundException, SQLIntegrityConstraintViolationException {
		when(questionRepository.existsById(19877)).thenReturn(true);
		doNothing().when(questionRepository).deleteById(19877);
		questionServiceJpaImplementation.removeQuestion(19877);
		verify(questionRepository, times(1)).deleteById(19877);
	}

	@Test
	void testFindQuestionInLibrary() {
		when(questionRepository.existsById(19876)).thenReturn(true);
		assertTrue(questionServiceJpaImplementation.findQuestion(19876));
	}

	@Test
	void testFindQuestionNotInLibrary() {
		when(questionRepository.existsById(19876)).thenReturn(false);
		assertFalse(questionServiceJpaImplementation.findQuestion(19876));
	}

	@Test
	void testGetQuestion() throws QuestionNotFoundException {
		Question question = new Question();
		question.setId(1);
		when(questionRepository.findById(1)).thenReturn(Optional.of(question));
		questionServiceJpaImplementation.getQuestion(1);
		verify(questionRepository).findById(1);
	}

	@Test
	void testGetQuestionNotPresentInQuestionLibrary() throws QuestionNotFoundException {
		when(questionRepository.findById(89754)).thenReturn(Optional.empty());
		assertThrows(QuestionNotFoundException.class,()->questionServiceJpaImplementation.getQuestion(89754));
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
		assertTrue(questionServiceJpaImplementation.displayQuestions().size() > 0);
	}

}
