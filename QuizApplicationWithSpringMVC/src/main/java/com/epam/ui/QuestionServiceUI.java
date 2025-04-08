package com.epam.ui;

import java.util.Map;
import java.util.function.Consumer;

public interface QuestionServiceUI {
	void createQuestion(int qNo);

	void updateQuestion(int qNo);

	Map<Integer, Runnable> getQuestionAccess();

	Map<Integer, Consumer<Integer>> getUpdateQuestion();
}
