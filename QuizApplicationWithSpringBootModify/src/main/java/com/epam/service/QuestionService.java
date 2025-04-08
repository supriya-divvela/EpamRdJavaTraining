package com.epam.service;

import java.util.List;
import java.util.Set;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;

public interface QuestionService {
	int addQuestion(Question question) throws DuplicateQuestionNumberException;

	int removeQuestion(int qNo) throws QuestionNotFoundException;

	List<Question> displayQuestions() throws EmptyQuestionLibraryException;

	int updateTitle(int qNo, String title) throws QuestionNotFoundException;

	int updateOptions(int qNo, List<String> options) throws QuestionNotFoundException;

	int updateDifficulty(int qNo, String difficulty) throws QuestionNotFoundException;

	int updateTaggingTopic(int qNo, String taggingTopic) throws QuestionNotFoundException;

	int updateAnswers(int qNo, Set<Integer> newAnswers) throws QuestionNotFoundException;

	int updateMarks(int qNo, int updateMarks) throws QuestionNotFoundException;

	boolean findQuestion(int qNo);

	boolean isEmptyQuestionLibrary();

	Question getQuestion(int qNo) throws QuestionNotFoundException;

}
