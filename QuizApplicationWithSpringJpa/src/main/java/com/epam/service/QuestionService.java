package com.epam.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;

public interface QuestionService {
	int addQuestion(QuestionDto question);

	int removeQuestion(int qNo) throws QuestionNotFoundException, SQLIntegrityConstraintViolationException;

	List<QuestionDto> displayQuestions();

	boolean findQuestion(int qNo);

	boolean isEmptyQuestionLibrary();

	Question getQuestion(int qNo) throws QuestionNotFoundException;

	void updateQuestion(int qNo, QuestionDto question) throws QuestionNotFoundException;

}
