package com.epam.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.jparepository.QuestionRepository;
import com.epam.model.Question;

import jakarta.transaction.Transactional;

@Component
@Primary
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;
	private Logger logger = LoggerFactory.getLogger("QuestionServiceImpl.class");

	@Override
	public QuestionDto addQuestion(QuestionDto questionDto) {
		logger.info("QuestionService:addQuestion");
		return modelMapper.map(questionRepository.save(modelMapper.map(questionDto, Question.class)),
				QuestionDto.class);
	}

	@Transactional
	@Override
	public void removeQuestion(int qNo) throws SQLIntegrityConstraintViolationException{
		logger.info("QuestionService:deleteQuestion");
		questionRepository.deleteById(qNo);
	}

	@Override
	public List<QuestionDto> displayQuestions() {
		return questionRepository.findAll().stream().map(question -> modelMapper.map(question, QuestionDto.class))
				.toList();

	}

	@Override
	public boolean findQuestion(int qNo) {
		return questionRepository.existsById(qNo);
	}

	@Override
	public boolean isEmptyQuestionLibrary() {
		return questionRepository.count() == 0;
	}

	@Override
	public QuestionDto getQuestion(int qNo) throws QuestionException {
		logger.error("QuestionService:get question successfull...");
		return modelMapper.map(questionRepository.findById(qNo).orElseThrow(() -> {
			logger.error("QuestionService:Cannot get question...As there is no question with questionNo {}", qNo);
			return new QuestionException("Question not found with this question number...");
		}), QuestionDto.class);

	}

	@Override
	public QuestionDto updateQuestion(QuestionDto questionDto) throws QuestionException {
		logger.info("QuestionService:update question successfull...");
		return modelMapper.map(questionRepository.findById(questionDto.getId())
				.map(question -> questionRepository.save(modelMapper.map(questionDto, Question.class)))
				.orElseThrow(() -> {
					logger.error("QuestionService:Cannot update question...As there is no question with questionNo {}",
							questionDto.getId());
					return new QuestionException("Question not found....");
				}), QuestionDto.class);
	}
}
