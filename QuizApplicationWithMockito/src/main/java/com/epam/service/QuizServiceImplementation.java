package com.epam.service;

import java.util.stream.Collectors;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuizDao;
import com.epam.repository.QuizDaoImplementation;

public class QuizServiceImplementation implements QuizService {
	private QuizDao quizDao = new QuizDaoImplementation();

	@Override
	public void addQuiz(Quiz quiz) throws DuplicateQuizNumberException {
		if (findQuiz(quiz.getQuizNo())) {
			throw new DuplicateQuizNumberException();
		}
		quizDao.addQuiz(quiz);
	}

	@Override
	public void removeQuiz(Quiz quiz) throws  QuizNotFoundException {
		if (!findQuiz(quiz.getQuizNo())) {
			throw new QuizNotFoundException();
		}
		quizDao.removeQuiz(quiz);

	}

	@Override
	public boolean isEmptyQuizLibrary() {
		return quizDao.getQuizsList().isEmpty();
	}

	@Override
	public boolean findQuiz(int quizNo)  {
		return quizDao.getQuizsList().stream().filter(quiz -> quiz.getQuizNo() == quizNo).count() == 1;
	}

	@Override
	public String displayAvaliableQuizes(){
		return quizDao.getQuizsList().stream().map(Object::toString).reduce("\n",
				(partialString, element) -> partialString + element + "\n");
	}

	@Override
	public Quiz getQuiz(int quizNo) throws QuizNotFoundException {
		if (!findQuiz(quizNo)) {
			throw new QuizNotFoundException();
		}
		return quizDao.getQuizsList().stream().filter(quizData -> quizData.getQuizNo() == quizNo)
				.collect(Collectors.toList()).get(0);
	}

	public boolean addQuestionToQuiz(Quiz quiz, Question question)
			throws DuplicateQuestionNumberException, QuestionNotFoundException {
		if (isQuestionPresentInQuiz(quiz, question.getQNo())) {
			throw new DuplicateQuestionNumberException();
		}
		return quiz.getListOfQuestions().add(question);
	}

	public boolean removeQuestionFromQuiz(Quiz quiz, Question question)
			throws QuestionNotFoundException {
		boolean questionRemoved=false;
		if (isQuestionPresentInQuiz(quiz, question.getQNo())) {
			questionRemoved=quiz.getListOfQuestions().remove(question);
			quizDao.addQuiz(quiz);
		}
		return questionRemoved;
	}

	public boolean isQuestionPresentInQuiz(Quiz quiz, int qNo) throws QuestionNotFoundException {
		return quiz.getListOfQuestions().stream().filter(quizData -> quizData.getQNo() == qNo).count() == 1;
	}
}
