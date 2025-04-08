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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.QuestionDto;
import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.repository.QuestionDao;
import com.epam.service.QuestionServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuestionService {
	@Mock
	private QuestionDao questionDao;
	@InjectMocks
	private QuestionServiceImplementation questionService;
	
	private List<Question> questionsList;

	@Test
	void testAddingDuplicateQuestion() throws DuplicateQuestionNumberException {
		QuestionDto questionDto = new QuestionDto();
		when(questionService.addQuestion(questionDto)).thenThrow(DuplicateQuestionNumberException.class);
		assertThrows(DuplicateQuestionNumberException.class, () -> questionService.addQuestion(questionDto));
	}

	@Test
	void testAddingNewQuestion() throws DuplicateQuestionNumberException {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setQNo(4);
		when(questionService.addQuestion(questionDto)).thenReturn(4);
		assertEquals(4, questionService.addQuestion(questionDto));
	}

	@Test
	void testUpdateQuestion() throws QuestionNotFoundException {
		Question question = new Question(4, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		QuestionDto questionDto = new QuestionDto(4, "hiii",
				new ArrayList<String>(
						Arrays.asList("a", "b", "c", "d")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		when(questionService.getQuestion(4)).thenReturn(question);
		questionService.updateQuestion(4, questionDto);
		assertEquals(questionService.getQuestion(4).toString(), questionDto.toString());
	}

	@Test
	void testEmptyLibrary() {
		List<Question> questions = new ArrayList<>();
		when(questionDao.getQuestionsList()).thenReturn(questions);
		assertTrue(questionService.isEmptyQuestionLibrary());
	}

	@Test
	void testNotEmptyLibrary() {
		questionsList = new ArrayList<>(Arrays.asList(new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertFalse(questionService.isEmptyQuestionLibrary());
	}

	@Test
	void testRemovingQuestionPresentInQuestionLibrary() throws QuestionNotFoundException {
		when(questionService.removeQuestion(1)).thenReturn(1);
		assertEquals(1, questionService.removeQuestion(1));
	}

	@Test
	void testRemovingQuestionPresentInLibrary() throws QuestionNotFoundException {
		when(questionService.removeQuestion(19877)).thenThrow(QuestionNotFoundException.class);
		assertThrows(QuestionNotFoundException.class, () -> questionService.removeQuestion(19877));
	}

	@Test
	void testFindQuestionInLibrary() {
		when(questionDao.findQuestion(1)).thenReturn(true);
		assertTrue(questionDao.findQuestion(1));
	}

	@Test
	void testFindQuestionNotInLibrary() {
		when(questionDao.findQuestion(19876)).thenReturn(false);
		assertFalse(questionService.findQuestion(19876));
	}

	@Test
	void testGetQuestion() throws QuestionNotFoundException {
		Question question = new Question();
		question.setQNo(1);
		when(questionDao.getQuestion(1)).thenReturn(question);
		assertEquals(question.toString(), questionService.getQuestion(1).toString());
	}

	@Test
	void testGetQuestionNotPresentInQuestionLibrary() throws QuestionNotFoundException {
		when(questionDao.getQuestion(89754)).thenThrow(QuestionNotFoundException.class);
		assertThrows(QuestionNotFoundException.class,()->questionService.getQuestion(89754));
	}

	@Test
	void testDisplayQuestions() throws EmptyQuestionLibraryException {
		questionsList = new ArrayList<>(Arrays.asList(new Question(1, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertTrue(questionService.displayQuestions().size() > 0);
	}

	@Test
	void testEmptyDisplayQuestions() throws EmptyQuestionLibraryException {
		questionsList = new ArrayList<>();
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(EmptyQuestionLibraryException.class, () -> questionService.displayQuestions());
	}
}
