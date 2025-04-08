package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.repository.QuestionDao;

@Component
public class QuestionServiceImplementation implements QuestionService {
	@Autowired
	private QuestionDao questionDao;

	public int addQuestion(QuestionDto questionDto) throws DuplicateQuestionNumberException {
		return questionDao.addQuestion(new Question(questionDto.getQNo(),questionDto.getTitle(),questionDto.getOptions(),questionDto.getDifficulty(),questionDto.getTaggingTopic(),questionDto.getAnswers(),questionDto.getMarks()));
	}

	@Override
	public int removeQuestion(int qNo) throws QuestionNotFoundException {
		return questionDao.removeQuestion(qNo);
	}

	@Override
	public List<Question> displayQuestions() throws EmptyQuestionLibraryException {
		if (isEmptyQuestionLibrary()) {
			throw new EmptyQuestionLibraryException();
		}
		return questionDao.getQuestionsList();
	}

	@Override
	public boolean findQuestion(int qNo) {
		return questionDao.findQuestion(qNo);
	}

	@Override
	public boolean isEmptyQuestionLibrary() {
		return questionDao.getQuestionsList().isEmpty();
	}

	@Override
	public Question getQuestion(int qNo) throws QuestionNotFoundException {
		return questionDao.getQuestion(qNo);
	}

	@Override
	public void updateQuestion(int qNo, QuestionDto question) throws QuestionNotFoundException {
		getQuestion(qNo).setTitle(question.getTitle());
		getQuestion(qNo).setDifficulty(question.getDifficulty());
		getQuestion(qNo).setAnswers(question.getAnswers());
		getQuestion(qNo).setTaggingTopic(question.getTaggingTopic());
		getQuestion(qNo).setMarks(question.getMarks());
		getQuestion(qNo).setOptions(question.getOptions());
	}

}
