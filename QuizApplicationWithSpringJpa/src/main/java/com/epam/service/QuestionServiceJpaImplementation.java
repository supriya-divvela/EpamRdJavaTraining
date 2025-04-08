package com.epam.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.jparepository.QuestionRepository;
import com.epam.model.Question;

import jakarta.transaction.Transactional;

@Component
@Primary
public class QuestionServiceJpaImplementation implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int addQuestion(QuestionDto questionDto) {
		return questionRepository.save(modelMapper.map(questionDto, Question.class)).getId();
	}

	@Transactional
	@Override
	public int removeQuestion(int qNo) throws QuestionNotFoundException, SQLIntegrityConstraintViolationException {
		if (!questionRepository.existsById(qNo)) {
			throw new QuestionNotFoundException("Question not found with this question number...");
		}
		questionRepository.deleteById(qNo);
		return qNo;
	}

	@Override
	public List<QuestionDto> displayQuestions() {
		return questionRepository.findAll().stream().map(question->modelMapper.map(question,QuestionDto.class)).toList();

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
	public Question getQuestion(int qNo) throws QuestionNotFoundException {
		return questionRepository.findById(qNo)
				.orElseThrow(() -> new QuestionNotFoundException("Question not found with this question number..."));

	}

	@Override
	public void updateQuestion(int qNo, QuestionDto questionDto) throws QuestionNotFoundException {
		questionDto.setId(qNo);
		questionRepository.save(modelMapper.map(questionDto, Question.class));
	}
}
