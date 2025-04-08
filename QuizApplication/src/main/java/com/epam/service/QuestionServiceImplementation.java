package com.epam.service;

import java.util.Set;
import java.util.stream.Collectors;

import com.epam.daorepository.QuestionDao;
import com.epam.model.Question;

public class QuestionServiceImplementation implements QuestionService {
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public boolean addQuestion(Question question) {
		return questionDao.getQuestionsList().add(question);
	}

	@Override
	public boolean isEmptyQuestionLibrary() {
		return questionDao.getQuestionsList().isEmpty();
	}

	@Override
	public boolean findQuestion(int qNo) {
		return questionDao.getQuestionsList().stream().filter(question -> question.getQNo() == qNo).count() == 1;
	}

	@Override
	public void removeQuestion(Question question) {
		questionDao.getQuestionsList().remove(question);
	}

	@Override
	public Question getQuestion(int qNo) {
		return questionDao.getQuestionsList().stream().filter(question -> question.getQNo() == qNo)
				.collect(Collectors.toList()).get(0);
	}

	@Override
	public String displayQuestions() {
		return questionDao.getQuestionsList().stream().map(question -> question.toString()).reduce("\n",
				(partialString, element) -> partialString + element + "\n");
	}

	@Override
	public void updateTitle(int qNo, String title) {
		getQuestion(qNo).setTitle(title);
	}

	@Override
	public void updateOptions(int qNo, Set<String> options) {
		getQuestion(qNo).setOptions(options);
	}

	@Override
	public void updateDifficulty(int qNo, String difficulty) {
		getQuestion(qNo).setDifficulty(difficulty);
	}

	@Override
	public void updateTaggingTopic(int qNo, String taggingTopic) {
		getQuestion(qNo).setTaggingTopic(taggingTopic);
	}

	@Override
	public void updateAnswers(Integer qNo, Set<Integer> newAnswers) {
		getQuestion(qNo).setAnswers(newAnswers);
	}
}
