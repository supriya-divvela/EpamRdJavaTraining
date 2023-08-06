package com.epam.service;

import java.util.List;
import java.util.Set;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;

public interface QuestionService {
	void addQuestion(Question question) throws DuplicateQuestionNumberException;

	void removeQuestion(Question question) throws QuestionNotFoundException;

	boolean isEmptyQuestionLibrary();

	boolean findQuestion(int questionNo);

	String displayQuestions() throws EmptyQuestionLibraryException;

	Question getQuestion(int questionNo) throws QuestionNotFoundException;

	void updateTitle(int qNo, String title) throws QuestionNotFoundException;

	void updateOptions(int qNo, List<String> options) throws QuestionNotFoundException;

	void updateDifficulty(int qNo, String difficulty) throws QuestionNotFoundException;

	void updateTaggingTopic(int qNo, String taggingTopic) throws QuestionNotFoundException;

	void updateAnswers(int qNo, Set<Integer> newAnswers) throws QuestionNotFoundException;

	void updateMarks(int qNo, int updateMarks) throws QuestionNotFoundException;

}
