package com.epam.service;

import com.epam.model.Question;
import com.epam.model.Quiz;

public interface QuizService {
	boolean addQuiz(Quiz quiz);

	void removeQuiz(Quiz quiz);

	boolean isEmptyQuizLibrary();

	boolean findQuiz(int quizNo);

	String displayAvaliableQuizes();

	Quiz getQuiz(int quizNo);

	void addQuestionToQuiz(Quiz quiz, Question question, int marks);

	void removeQuestionFromQuiz(Quiz quiz, Question question);

	boolean isQuestionPresentInQuiz(Quiz quiz, int qNo);
}
