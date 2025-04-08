package com.epam.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;

@Component
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");

	@Override
	public int calculateMarks(List<List<Integer>> answers, QuizDto quiz) {
		logger.info("UserService:calculate marks method..");
		return IntStream.range(0, answers.size())
				.filter(i -> answers.get(i).equals(quiz.getListOfQuestions().get(i).getAnswers().stream().toList()))
				.map(i -> quiz.getListOfQuestions().get(i).getMarks()).sum();

	}

	@Override
	public int calculateTotalMarks(QuizDto quiz) {
		logger.info("UserService:calculate total marks of quiz ..");
		return quiz.getListOfQuestions().stream().map(QuestionDto::getMarks)
				.collect(Collectors.summingInt(Integer::intValue));
	}

}
