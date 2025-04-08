package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import lombok.extern.slf4j.Slf4j;

@Component
@Primary
@Slf4j
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public QuizDto addQuiz(QuizDto quizDto) {
		log.info("QuizService:Add Quiz Method...");
		return modelMapper.map(quizRepository.save(modelMapper.map(quizDto, Quiz.class)), QuizDto.class);
	}

	@Transactional
	@Override
	public void removeQuiz(int quizNo) throws QuizException {
		log.info("QuizService:Remove Quiz Method...");
		quizRepository.deleteById(quizNo);
	}

	@Override
	public List<QuizDto> displayAvaliableQuizes() {
		log.info("QuizService:disaplayAvailableQuizzes");
		return quizRepository.findAll().stream().map(quiz -> modelMapper.map(quiz, QuizDto.class)).toList();
	}

	@Override
	public QuizDto getQuiz(int quizNo) throws QuizException {
		log.info("QuizService:Get Quiz Method..");
		return modelMapper.map(quizRepository.findById(quizNo)
				.orElseThrow(() -> new QuizException("Quiz not found with this quiz number.....")), QuizDto.class);
	}

	@Override
	public boolean existQuiz(int quizNo) {
		log.info("QuizService:exist Quiz");
		return quizRepository.existsById(quizNo);
	}

	@Override
	public boolean isEmptyQuizLibrary() {
		log.info("QuizService:Empty Quiz Library");
		return quizRepository.count() == 0;
	}

	@Override
	public QuizDto createQuiz(String title, List<Integer> listOfQuestionNumbers) throws QuestionException {
		log.info("QuizService:create Quiz");
		if (questionRepository.findAllById(listOfQuestionNumbers).isEmpty()) {
			throw new QuestionException("Please enter proper numbers..");
		}
		return new QuizDto(title, questionRepository.findAllById(listOfQuestionNumbers).stream()
				.map(question -> modelMapper.map(question, QuestionDto.class)).toList());
	}

	@Override
	public QuizDto updateQuiz(QuizDto quizDto) throws QuizException {
		log.info("QuizService:update Quiz");
		return modelMapper.map(quizRepository.findById(quizDto.getId())
				.map(quiz -> quizRepository.save(modelMapper.map(quizDto, Quiz.class)))
				.orElseThrow(() -> new QuizException("Quiz not found with this quiz number...")), QuizDto.class);
	}
}
