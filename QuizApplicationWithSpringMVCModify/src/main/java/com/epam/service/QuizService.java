package com.epam.service;

import java.util.List;

import com.epam.dto.QuizDto;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.EmptyQuizLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Quiz;

public interface QuizService {
	int addQuiz(QuizDto quiz) throws DuplicateQuizNumberException;

	int removeQuiz(int quizNo) throws QuizNotFoundException;

	List<Quiz> displayAvaliableQuizes() throws EmptyQuizLibraryException;

	Quiz getQuiz(int quizNo) throws QuizNotFoundException;

	boolean findQuiz(int quizNo);

	boolean isEmptyQuizLibrary();

	QuizDto createQuiz(int quizNo, String title, List<Integer> listOfQuestionNumbers)
			throws QuestionNotFoundException, DuplicateQuizNumberException, EmptyQuestionLibraryException;

	void updateQuiz(int quizNo, QuizDto quiz) throws QuizNotFoundException;
}
