package com.epam.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

	@Override
	public int calculateMarks(List<List<Integer>> answers, QuizDto quiz) {
		log.info("UserService:calculate marks method..");
		return IntStream.range(0, answers.size())
				.filter(i -> answers.get(i).equals(quiz.getListOfQuestions().get(i).getAnswers().stream().toList()))
				.map(i -> quiz.getListOfQuestions().get(i).getMarks()).sum();

	}

	@Override
	public int calculateTotalMarks(QuizDto quiz) {
		log.info("UserService:calculate total marks of quiz ..");
		return quiz.getListOfQuestions().stream().map(QuestionDto::getMarks)
				.collect(Collectors.summingInt(Integer::intValue));
	}

}
