package com.epam.service;

import java.util.stream.Collectors;

import com.epam.daorepository.QuizDao;
import com.epam.model.Question;
import com.epam.model.Quiz;

public class QuizServiceImplementation implements QuizService {
	private QuizDao quizDao = new QuizDao();

	@Override
	public boolean addQuiz(Quiz quiz) {
		return quizDao.getQuizsList().add(quiz);
	}

	@Override
	public void removeQuiz(Quiz quiz) {
		quizDao.getQuizsList().remove(quiz);
	}

	@Override
	public boolean isEmptyQuizLibrary() {
		return quizDao.getQuizsList().isEmpty();
	}

	@Override
	public boolean findQuiz(int quizNo) {
		return quizDao.getQuizsList().stream().filter(quiz -> quiz.getQuizNo() == quizNo).count() == 1;
	}

	@Override
	public String displayAvaliableQuizes() {
		return quizDao.getQuizsList().stream().map(quiz -> quiz.toString()).reduce("\n",
				(partialString, element) -> partialString + element + "\n");
	}

	@Override
	public Quiz getQuiz(int quizNo) {
		return quizDao.getQuizsList().stream().filter(quizData -> quizData.getQuizNo() == quizNo)
				.collect(Collectors.toList()).get(0);
	}

	public void addQuestionToQuiz(Quiz quiz, Question question, int marks) {
		quiz.getListOfQuestions().put(question, marks);
	}

	public void removeQuestionFromQuiz(Quiz quiz, Question question) {
		quiz.getListOfQuestions().remove(question);
	}

	public boolean isQuestionPresentInQuiz(Quiz quiz, int qNo) {
		return quiz.getListOfQuestions().entrySet().stream().filter(quizData -> quizData.getKey().getQNo() == qNo)
				.count() == 1;
	}
}
