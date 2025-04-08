package com.epam.service;

import java.util.List;

import com.epam.dto.QuizDto;
import com.epam.exception.QuestionException;
import com.epam.exception.QuizException;

public interface QuizService {
	QuizDto addQuiz(QuizDto quiz);

	void removeQuiz(int quizNo) throws QuizException;

	List<QuizDto> displayAvaliableQuizes();

	QuizDto getQuiz(int quizNo) throws QuizException;

	boolean existQuiz(int quizNo);

	boolean isEmptyQuizLibrary();

	QuizDto createQuiz(String title, List<Integer> listOfQuestionNumbers)
			throws QuestionException;

	QuizDto updateQuiz(QuizDto quiz) throws QuizException;
}
