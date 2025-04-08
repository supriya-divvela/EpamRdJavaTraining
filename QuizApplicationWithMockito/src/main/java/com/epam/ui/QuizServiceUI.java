package com.epam.ui;

import com.epam.exception.EmptyQuestionLibraryException;

public interface QuizServiceUI {
	void createQuiz(int quizNo);

	void updateQuiz(int quizNo) throws EmptyQuestionLibraryException;
}
