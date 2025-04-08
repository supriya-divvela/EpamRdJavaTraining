package com.epam.restcontroller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.service.QuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("questionspage")
public class QuestionApi {
	@Autowired
	private QuestionService questionService;
	private Logger logger = LoggerFactory.getLogger("QuestionApi.class");

	@GetMapping("questions")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<QuestionDto>> getQuestions() {
		logger.info("QuestionApi:Get All Questions..");
		return new ResponseEntity<>(questionService.displayQuestions(), HttpStatus.OK);
	}

	@GetMapping("questions/{id}")
	public ResponseEntity<QuestionDto> getQuestion(@PathVariable("id") int questionId)
			throws QuestionException {
		logger.info("QuestionApi:Get one certain Questions..");
		return new ResponseEntity<>(questionService.getQuestion(questionId), HttpStatus.OK);

	}

	@PostMapping("questions")
	public ResponseEntity<QuestionDto> addQuestion(@RequestBody @Valid QuestionDto questionDto) {
		logger.info("QuestionApi:Add Question..");
		return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.CREATED);
	}

	@DeleteMapping("questions/{id}")
	public ResponseEntity<QuestionDto> deleteQuestion(@PathVariable("id") int questionId)
			throws SQLIntegrityConstraintViolationException, QuestionException {
		logger.info("QuestionApi:delete Questions..");
		return ResponseEntity.noContent().build();
	}

	@PutMapping("questions")
	public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Valid QuestionDto questionDto)
			throws QuestionException {
		logger.info("QuestionApi:update Questions..");
		return new ResponseEntity<>(questionService.updateQuestion(questionDto), HttpStatus.ACCEPTED);
	}
}
