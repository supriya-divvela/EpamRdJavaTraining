package com.epam.service;

import java.util.List;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;

public interface QuizService {
	int addQuiz(Quiz quiz) throws DuplicateQuizNumberException;

	int removeQuiz(int quizNo) throws QuizNotFoundException;

	List<Quiz> displayAvaliableQuizes();

	Quiz getQuiz(int quizNo) throws QuizNotFoundException;

	boolean addQuestionToQuiz(Quiz quiz, Question question)
			throws DuplicateQuestionNumberException, QuestionNotFoundException;

	boolean removeQuestionFromQuiz(Quiz quiz, Question question) throws QuestionNotFoundException, DuplicateQuizNumberException, QuizNotFoundException;

	boolean isQuestionPresentInQuiz(Quiz quiz, int qNo) throws QuestionNotFoundException;

	boolean findQuiz(int quizNo);

	boolean isEmptyQuizLibrary();
}
