package com.epam.repository;

import java.util.List;

import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Quiz;

public interface QuizDao {
	List<Quiz> getQuizsList();

	int addQuiz(Quiz quiz) throws DuplicateQuizNumberException;

	int removeQuiz(int quizNo) throws QuizNotFoundException;

	boolean findQuiz(int quizNo);

	Quiz getQuiz(int quizNo) throws QuizNotFoundException;

}
