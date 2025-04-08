package com.epam.service;

import java.util.List;

import com.epam.dto.QuizDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Quiz;

public interface QuizService {
	int addQuiz(QuizDto quiz);

	int removeQuiz(int quizNo) throws QuizNotFoundException;

	List<Quiz> displayAvaliableQuizes();

	Quiz getQuiz(int quizNo) throws QuizNotFoundException;

	boolean findQuiz(int quizNo);

	boolean isEmptyQuizLibrary();

	QuizDto createQuiz(String title, List<Integer> listOfQuestionNumbers)
			throws QuestionNotFoundException;

	void updateQuiz(int quizNo, QuizDto quiz) throws QuizNotFoundException;
}
