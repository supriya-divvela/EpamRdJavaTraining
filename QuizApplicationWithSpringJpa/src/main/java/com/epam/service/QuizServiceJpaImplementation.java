package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.dto.QuizDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.jparepository.QuestionRepository;
import com.epam.jparepository.QuizRepository;
import com.epam.model.Quiz;

@Component
@Primary
public class QuizServiceJpaImplementation implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int addQuiz(QuizDto quizDto) {
		return quizRepository.save(modelMapper.map(quizDto, Quiz.class)).getId();
	}

	@Override
	public int removeQuiz(int quizNo) throws QuizNotFoundException {
		if (!quizRepository.existsById(quizNo)) {
			throw new QuizNotFoundException("Quiz not found with this quiz number...");
		}
		quizRepository.deleteById(quizNo);
		return quizNo;
	}

	@Override
	public List<Quiz> displayAvaliableQuizes(){
		return quizRepository.findAll();
	}

	@Override
	public Quiz getQuiz(int quizNo) throws QuizNotFoundException {
		return quizRepository.findById(quizNo).orElseThrow(()->new QuizNotFoundException("Quiz not found with this quiz number..."));
	}

	@Override
	public boolean findQuiz(int quizNo) {
		return quizRepository.existsById(quizNo);
	}

	@Override
	public boolean isEmptyQuizLibrary() {
		return quizRepository.count() == 0;
	}

	@Override
	public QuizDto createQuiz(String title, List<Integer> listOfQuestionNumbers)
			throws QuestionNotFoundException {
		return new QuizDto(title, questionRepository.findAllById(listOfQuestionNumbers));
	}

	@Override
	public void updateQuiz(int quizNo, QuizDto quizDto) throws QuizNotFoundException {
		quizDto.setId(quizNo);
		quizRepository.save(modelMapper.map(quizDto, Quiz.class));
	}
}
