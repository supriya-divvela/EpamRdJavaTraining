package com.epam.service;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;

public interface QuizService {
	void addQuiz(Quiz quiz) throws DuplicateQuizNumberException;

	void removeQuiz(Quiz quiz) throws QuizNotFoundException;

	boolean isEmptyQuizLibrary();

	boolean findQuiz(int quizNo) throws QuizNotFoundException;

	String displayAvaliableQuizes();

	Quiz getQuiz(int quizNo) throws QuizNotFoundException;

	boolean addQuestionToQuiz(Quiz quiz, Question question)
			throws DuplicateQuestionNumberException, QuestionNotFoundException;

	boolean removeQuestionFromQuiz(Quiz quiz, Question question) throws QuestionNotFoundException;

	boolean isQuestionPresentInQuiz(Quiz quiz, int qNo) throws QuestionNotFoundException;
}
