package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuizDao;

@Service
public class QuizServiceImplementation implements QuizService {
	@Autowired
	private QuizDao quizDao;
	
	@Override
	public int addQuiz(Quiz quiz) throws DuplicateQuizNumberException {
		return quizDao.addQuiz(quiz);
	}

	@Override
	public int removeQuiz(int quizNo) throws QuizNotFoundException {
		return quizDao.removeQuiz(quizNo);
	}

	@Override
	public List<Quiz> displayAvaliableQuizes() {
		return quizDao.getQuizsList();
	}

	@Override
	public Quiz getQuiz(int quizNo) throws QuizNotFoundException {
		return quizDao.getQuiz(quizNo);
	}

	@Override
	public boolean addQuestionToQuiz(Quiz quiz, Question question)
			throws DuplicateQuestionNumberException, QuestionNotFoundException {
		if (isQuestionPresentInQuiz(quiz, question.getQNo())) {
			throw new DuplicateQuestionNumberException();
		}
		return quiz.getListOfQuestions().add(question);
	}

	@Override
	public boolean removeQuestionFromQuiz(Quiz quiz, Question question)
			throws QuestionNotFoundException, DuplicateQuizNumberException, QuizNotFoundException {
		boolean questionRemoved = false;
		if (isQuestionPresentInQuiz(quiz, question.getQNo())) {
			questionRemoved = quiz.getListOfQuestions().remove(question);
			quizDao.updateQuiz(quiz.getQuizNo());
		}
		return questionRemoved;
	}

	@Override
	public boolean isQuestionPresentInQuiz(Quiz quiz, int qNo) throws QuestionNotFoundException {
		return quiz.getListOfQuestions().stream().filter(quizData -> quizData.getQNo() == qNo).count() == 1;
	}

	@Override
	public boolean findQuiz(int quizNo) {
		return quizDao.findQuiz(quizNo);
	}

	@Override
	public boolean isEmptyQuizLibrary() {
		return quizDao.getQuizsList().isEmpty();
	}
}
