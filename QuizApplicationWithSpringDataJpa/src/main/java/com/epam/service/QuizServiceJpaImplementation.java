package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dto.QuizDto;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.EmptyQuizLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.jparepository.QuestionRepository;
import com.epam.jparepository.QuizRepository;
import com.epam.model.Question;
import com.epam.model.Quiz;
@Component
public class QuizServiceJpaImplementation implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public int addQuiz(QuizDto quiz) throws DuplicateQuizNumberException {
		return quizRepository.save(new Quiz(quiz.getQuizNo(), quiz.getTitle(), quiz.getListOfQuestions())).getQuizNo();
	}

	@Override
	public int removeQuiz(int quizNo) throws QuizNotFoundException {
		if (!quizRepository.existsByQuizNo(quizNo)) {
			throw new QuizNotFoundException();
		}
		quizRepository.deleteByQuizNo(quizNo);
		return quizNo;
	}

	@Override
	public List<Quiz> displayAvaliableQuizes() throws EmptyQuizLibraryException {
		return quizRepository.findAll();
	}

	@Override
	public Quiz getQuiz(int quizNo) throws QuizNotFoundException {
		return quizRepository.findByQuizNo(quizNo);
	}

	@Override
	public boolean findQuiz(int quizNo) {
		return quizRepository.existsByQuizNo(quizNo);
	}

	@Override
	public boolean isEmptyQuizLibrary() {
		return quizRepository.count()==0;
	}

	@Override
	public QuizDto createQuiz(int quizNo, String title, List<Integer> listOfQuestionNumbers)
			throws QuestionNotFoundException, DuplicateQuizNumberException, EmptyQuestionLibraryException {
		List<Question> listOfQuestions = new ArrayList<>();
		for (int qNo : listOfQuestionNumbers) {
			if (questionRepository.existsByQNo(qNo)) {
				listOfQuestions.add(questionRepository.findByQNo(qNo));
			}
		}
		return new QuizDto(quizNo, title, listOfQuestions);
	}

	@Override
	public void updateQuiz(int quizNo, QuizDto quiz) throws QuizNotFoundException {
		quizRepository.findByQuizNo(quizNo).setTitle(quiz.getTitle());
		quizRepository.findByQuizNo(quizNo).setListOfQuestions(quiz.getListOfQuestions());
	}

}
