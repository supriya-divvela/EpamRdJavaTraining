package com.epam.service;

import java.util.List;

import com.epam.dto.QuestionDto;
import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;

public interface QuestionService {
	int addQuestion(QuestionDto question) throws DuplicateQuestionNumberException;

	int removeQuestion(int qNo) throws QuestionNotFoundException;

	List<Question> displayQuestions() throws EmptyQuestionLibraryException;

	boolean findQuestion(int qNo);

	boolean isEmptyQuestionLibrary();

	Question getQuestion(int qNo) throws QuestionNotFoundException;

	void updateQuestion(int qNo, QuestionDto question) throws QuestionNotFoundException;

}
