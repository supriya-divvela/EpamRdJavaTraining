package com.epam.repository;

import java.util.List;

import com.epam.model.Question;

public interface QuestionDao {
	List<Question> getQuestionsList();

	void addQuestion(Question question);

	void removeQuestion(Question question);
	
	void updateQuestion(Question question);
}
