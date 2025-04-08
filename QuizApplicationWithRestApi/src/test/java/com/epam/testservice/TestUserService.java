package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestUserService {

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testCalculateMarks() {
		List<List<Integer>> answers = List.of(List.of(1, 2, 3, 4), List.of(1, 2));
		List<QuestionDto> questionsList = new ArrayList<>();
		QuestionDto question = new QuestionDto();
		question.setMarks(4);
		question.setAnswers(new TreeSet<>(List.of(1, 2, 3, 4)));
		questionsList.add(question);
		QuestionDto question2 = new QuestionDto();
		question2.setMarks(4);
		question2.setAnswers(new TreeSet<>(List.of(1, 2)));
		questionsList.add(question2);
		QuizDto quiz = new QuizDto();
		quiz.setListOfQuestions(questionsList);
		assertEquals(8, userService.calculateMarks(answers, quiz));
	}

	@Test
	void testCalculateTotalMarks() {
		List<QuestionDto> questionsList = new ArrayList<>();
		QuestionDto question = new QuestionDto();
		question.setMarks(4);
		questionsList.add(question);
		QuizDto quiz = new QuizDto();
		quiz.setListOfQuestions(questionsList);
		assertEquals(4, userService.calculateTotalMarks(quiz));
	}
}
