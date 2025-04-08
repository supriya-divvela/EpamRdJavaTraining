package com.epam.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.repository.QuestionDao;

@Component
public class QuestionServiceImplementation implements QuestionService {
	@Autowired
	private QuestionDao questionDao;

	@Override
	public int addQuestion(Question question) throws DuplicateQuestionNumberException {
		return questionDao.addQuestion(question);
	}

	@Override
	public int removeQuestion(int qNo) throws QuestionNotFoundException {
		return questionDao.removeQuestion(qNo);
	}

	@Override
	public List<Question> displayQuestions() throws EmptyQuestionLibraryException {
		return questionDao.getQuestionsList();
	}

	@Override
	public int updateTitle(int qNo, String title) throws QuestionNotFoundException {
		if (!questionDao.findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		questionDao.getQuestion(qNo).setTitle(title);
		return questionDao.updateQuestion(qNo);
	}

	@Override
	public int updateOptions(int qNo, List<String> options) throws QuestionNotFoundException {
		if (!questionDao.findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		questionDao.getQuestion(qNo).setOptions(options);
		return questionDao.updateQuestion(qNo);
	}

	@Override
	public int updateDifficulty(int qNo, String difficulty) throws QuestionNotFoundException {
		if (!questionDao.findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		questionDao.getQuestion(qNo).setDifficulty(difficulty);
		return questionDao.updateQuestion(qNo);
	}

	@Override
	public int updateTaggingTopic(int qNo, String taggingTopic) throws QuestionNotFoundException {
		if (!questionDao.findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		questionDao.getQuestion(qNo).setTaggingTopic(taggingTopic);
		return questionDao.updateQuestion(qNo);
	}

	@Override
	public int updateAnswers(int qNo, Set<Integer> newAnswers) throws QuestionNotFoundException {
		if (!questionDao.findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		questionDao.getQuestion(qNo).setAnswers(newAnswers);
		return questionDao.updateQuestion(qNo);
	}

	@Override
	public int updateMarks(int qNo, int updateMarks) throws QuestionNotFoundException {
		if (!questionDao.findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		questionDao.getQuestion(qNo).setMarks(updateMarks);
		return questionDao.updateQuestion(qNo);
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

}
