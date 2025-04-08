package com.epam.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.epam.model.Question;
import com.epam.model.Quiz;

@Component
public class UserServiceImplementation implements UserService {

	@Override
	public int calculateMarks(List<List<Integer>> answers, Quiz quiz) {
		return IntStream.range(0, answers.size())
				.filter(i -> answers.get(i).equals(quiz.getListOfQuestions().get(i).getAnswers().stream().toList()))
				.map(i -> quiz.getListOfQuestions().get(i).getMarks()).sum();

	}

	@Override
	public int calculateTotalMarks(Quiz quiz) {
		return quiz.getListOfQuestions().stream().map(Question::getMarks)
				.collect(Collectors.summingInt(Integer::intValue));
	}

}
