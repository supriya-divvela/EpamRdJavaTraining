package com.epam.ui;

import java.util.Map;
import java.util.function.Consumer;

import com.epam.exception.EmptyQuestionLibraryException;

public interface QuizServiceUI {
	void createQuiz(int quizNo);

	void updateQuiz(int quizNo) throws EmptyQuestionLibraryException;

	Map<Integer, Consumer<Integer>> getUpdateQuiz();

	Map<Integer, Runnable> getQuizAccess();
}
