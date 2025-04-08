package com.epam.service;

import java.util.Set;

import com.epam.model.Question;

public interface QuestionService {
	boolean addQuestion(Question question);

	void removeQuestion(Question question);

	boolean isEmptyQuestionLibrary();

	boolean findQuestion(int questionNo);

	String displayQuestions();

	Question getQuestion(int questionNo);

	void updateTitle(int qNo,String title);

	void updateOptions(int qNo,Set<String> options);

	void updateDifficulty(int qNo,String difficulty);

	void updateTaggingTopic(int qNo,String taggingTopic);

	void updateAnswers(Integer qNo, Set<Integer> newAnswers);

}
