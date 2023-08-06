package com.epam.service;

import java.util.List;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;

public interface QuestionService {
	QuestionDto addQuestion(QuestionDto question) throws QuestionException;

	void removeQuestion(int i);

	List<QuestionDto> displayQuestions();

	boolean findQuestion(int qNo);

	boolean isEmptyQuestionLibrary();

	QuestionDto getQuestion(int qNo) throws QuestionException;

	QuestionDto updateQuestion(QuestionDto question) throws QuestionException;

}
