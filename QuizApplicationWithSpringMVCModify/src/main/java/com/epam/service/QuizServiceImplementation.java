package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.QuizDto;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.EmptyQuizLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuizDao;

@Service
public class QuizServiceImplementation implements QuizService {
	@Autowired
	private QuizDao quizDao;
	@Autowired
	private QuestionService questionService;

	@Override
	public QuizDto createQuiz(int quizNo, String title, List<Integer> listOfQuestionNumbers)
			throws QuestionNotFoundException, DuplicateQuizNumberException, EmptyQuestionLibraryException {
		List<Question> listOfQuestions = new ArrayList<>();
		for (int qNo : listOfQuestionNumbers) {
			if (questionService.findQuestion(qNo)) {
				listOfQuestions.add(questionService.getQuestion(qNo));
			}
		}
		return new QuizDto(quizNo, title, listOfQuestions);
	}

	@Override
	public int addQuiz(QuizDto quiz) throws DuplicateQuizNumberException {
		return quizDao.addQuiz(new Quiz(quiz.getQuizNo(),quiz.getTitle(),quiz.getListOfQuestions()));
	}

	@Override
	public int removeQuiz(int quizNo) throws QuizNotFoundException {
		return quizDao.removeQuiz(quizNo);
	}

	@Override
	public List<Quiz> displayAvaliableQuizes() throws EmptyQuizLibraryException {
		return quizDao.getQuizsList();
	}

	@Override
	public Quiz getQuiz(int quizNo) throws QuizNotFoundException {
		return quizDao.getQuiz(quizNo);
	}

	@Override
	public void updateQuiz(int quizNo, QuizDto quiz) throws QuizNotFoundException {
		getQuiz(quizNo).setTitle(quiz.getTitle());
		getQuiz(quizNo).setListOfQuestions(quiz.getListOfQuestions());
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
