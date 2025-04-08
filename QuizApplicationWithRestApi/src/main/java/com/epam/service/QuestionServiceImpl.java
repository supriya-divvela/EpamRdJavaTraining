package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.jparepository.QuestionRepository;
import com.epam.model.Question;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Component
@Primary
@Slf4j
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public QuestionDto addQuestion(QuestionDto questionDto) throws QuestionException {
		log.info("QuestionService:addQuestion");
		if (questionDto.getAnswers().stream()
				.allMatch(answer -> answer <= questionDto.getOptions().size() && answer > 0)) {
			return modelMapper.map(questionRepository.save(modelMapper.map(questionDto, Question.class)),
					QuestionDto.class);
		} else {
			throw new QuestionException("Answers should be less than or equals to options size");
		}
	}

	@Transactional
	@Override
	public void removeQuestion(int qNo) {
		log.info("QuestionService:deleteQuestion");
		questionRepository.deleteById(qNo);
	}

	@Override
	public List<QuestionDto> displayQuestions() {
		log.info("QuestionService:DisplayQuestions");
		return questionRepository.findAll().stream().map(question -> modelMapper.map(question, QuestionDto.class))
				.toList();

	}

	@Override
	public boolean findQuestion(int qNo) {
		log.info("QuestionService:FindQuestion");
		return questionRepository.existsById(qNo);
	}

	@Override
	public boolean isEmptyQuestionLibrary() {
		log.info("QuestionService:EmptyQuestionLibrary");
		return questionRepository.count() == 0;
	}

	@Override
	public QuestionDto getQuestion(int qNo) throws QuestionException {
		log.info("QuestionService:get question successfull...");
		return modelMapper.map(
				questionRepository.findById(qNo)
						.orElseThrow(() -> new QuestionException("Question not found with this question number...")),
				QuestionDto.class);

	}

	@Override
	public QuestionDto updateQuestion(QuestionDto questionDto) throws QuestionException {
		log.info("QuestionService:update question successfull...");
		return modelMapper.map(questionRepository.findById(questionDto.getId())
				.map(question -> questionRepository.save(modelMapper.map(questionDto, Question.class)))
				.orElseThrow(() -> new QuestionException("Question not found....")), QuestionDto.class);
	}
}
