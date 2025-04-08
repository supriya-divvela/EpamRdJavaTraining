package com.epam.repository;

import java.util.List;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;

public interface QuestionDao {
	List<Question> getQuestionsList();

	int addQuestion(Question question) throws DuplicateQuestionNumberException;

	int removeQuestion(int qNo) throws QuestionNotFoundException;

	Question getQuestion(int qNo) throws QuestionNotFoundException;

	boolean findQuestion(int qNo);


}
