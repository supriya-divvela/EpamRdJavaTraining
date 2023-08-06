package com.epam.repository;

import java.util.List;

import com.epam.model.Quiz;

public interface QuizDao {
	List<Quiz> getQuizsList();

	void addQuiz(Quiz quiz);

	void removeQuiz(Quiz quiz);
}
