package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
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
	public Quiz createQuiz(int quizNo, String title, List<Integer> listOfQuestionNumbers) throws QuestionNotFoundException, DuplicateQuizNumberException, EmptyQuestionLibraryException {
		List<Question> listOfQuestions=new ArrayList<>();
		for(int qNo:listOfQuestionNumbers) {
			if(questionService.findQuestion(qNo)) {
				listOfQuestions.add(questionService.getQuestion(qNo));
			}
		}
		return new Quiz(quizNo,title,listOfQuestions);
	}
	
	@Override
	public void addQuiz(Quiz quiz) throws DuplicateQuizNumberException {
		if (findQuiz(quiz.getQuizNo())) {
			throw new DuplicateQuizNumberException();
		}
		quizDao.addQuiz(quiz);
	}

	@Override
	public void removeQuiz(Quiz quiz)  {
		quizDao.removeQuiz(quiz);

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
		return quizDao.getQuizsList().stream().map(Object::toString).reduce("\n",
				(partialString, element) -> partialString + element + "\n");
	}

	@Override
	public Quiz getQuiz(int quizNo) {
		return quizDao.getQuizsList().stream().filter(quizData -> quizData.getQuizNo() == quizNo).toList().get(0);
	}

	public boolean addQuestionToQuiz(Quiz quiz, Question question)
			throws DuplicateQuestionNumberException, QuestionNotFoundException {
		if (isQuestionPresentInQuiz(quiz, question.getQNo())) {
			throw new DuplicateQuestionNumberException();
		}
		return quiz.getListOfQuestions().add(question);
	}

	public boolean removeQuestionFromQuiz(Quiz quiz, Question question) throws QuestionNotFoundException {
		boolean questionRemoved = false;
		if (isQuestionPresentInQuiz(quiz, question.getQNo())) {
			questionRemoved = quiz.getListOfQuestions().remove(question);
			quizDao.addQuiz(quiz);
		}
		return questionRemoved;
	}

	public boolean isQuestionPresentInQuiz(Quiz quiz, int qNo) throws QuestionNotFoundException {
		return quiz.getListOfQuestions().stream().filter(quizData -> quizData.getQNo() == qNo).count() == 1;
	}
	
	@Override
	public List<Quiz> viewQuizs(){
		return quizDao.getQuizsList();
	}

	@Override
	public void updateQuiz(int quizNo, Quiz quiz) {
		getQuiz(quizNo).setTitle(quiz.getTitle());
		getQuiz(quizNo).setListOfQuestions(quiz.getListOfQuestions());
	}
}
