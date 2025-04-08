package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.exception.QuestionException;
import com.epam.exception.QuizException;
import com.epam.jparepository.QuestionRepository;
import com.epam.jparepository.QuizRepository;
import com.epam.model.Quiz;

import jakarta.transaction.Transactional;

@Component
@Primary
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;
	private Logger logger = LoggerFactory.getLogger("QuizServiceImpl.class");

	@Override
	public QuizDto addQuiz(QuizDto quizDto) {
		logger.info("QuizService:Add Quiz Method...");
		return modelMapper.map(quizRepository.save(modelMapper.map(quizDto, Quiz.class)), QuizDto.class);
	}

	@Transactional
	@Override
	public void removeQuiz(int quizNo) throws QuizException {
		logger.info("QuizService:Remove Quiz Method...");
		quizRepository.deleteById(quizNo);
	}

	@Override
	public List<QuizDto> displayAvaliableQuizes() {
		return quizRepository.findAll().stream().map(quiz -> modelMapper.map(quiz, QuizDto.class)).toList();
	}

	@Override
	public QuizDto getQuiz(int quizNo) throws QuizException {
		logger.info("QuizService:Get Quiz Method..");
		return modelMapper.map(quizRepository.findById(quizNo).orElseThrow(() -> {
			logger.error("QuizService:Cannot get quiz...as quizno {} is not found..", quizNo);
			return new QuizException("Quiz not found with this quiz number.....");
		}), QuizDto.class);
	}

	@Override
	public boolean existQuiz(int quizNo) {
		return quizRepository.existsById(quizNo);
	}

	@Override
	public boolean isEmptyQuizLibrary() {
		return quizRepository.count() == 0;
	}

	@Override
	public QuizDto createQuiz(String title, List<Integer> listOfQuestionNumbers) throws QuestionException {
		if (questionRepository.findAllById(listOfQuestionNumbers).isEmpty()) {
			logger.error("Cannot create quiz as question numbers are not available in quiz");
			throw new QuestionException("Please enter proper numbers..");
		}
		return new QuizDto(title, questionRepository.findAllById(listOfQuestionNumbers).stream()
				.map(question -> modelMapper.map(question, QuestionDto.class)).toList());
	}

	@Override
	public QuizDto updateQuiz(QuizDto quizDto) throws QuizException {
		return modelMapper.map(quizRepository.findById(quizDto.getId())
				.map(quiz -> quizRepository.save(modelMapper.map(quizDto, Quiz.class))).orElseThrow(() -> {
					logger.info("QuizService:Cannot update quiz...as quizno {} is not found..", quizDto.getId());
					return new QuizException("Quiz not found with this quiz number...");
				}), QuizDto.class);
	}
}
