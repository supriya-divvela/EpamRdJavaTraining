package com.epam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.service.QuestionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/questionspage")
@Slf4j
public class QuestionApi {
	@Autowired
	private QuestionService questionService;

	@GetMapping("/questions")
	public ResponseEntity<List<QuestionDto>> getQuestions() {
		log.info("QuestionApi:Get All Questions..");
		return new ResponseEntity<>(questionService.displayQuestions(), HttpStatus.OK);
//		return ResponseEntity.ok().body(questionService.displayQuestions());

	}

	@GetMapping("questions/{id}")
	public ResponseEntity<QuestionDto> getQuestion(@PathVariable("id") int questionId) throws QuestionException {
		log.info("QuestionApi:Get one certain Questions..");
		return new ResponseEntity<>(questionService.getQuestion(questionId), HttpStatus.OK);
	}

	@PostMapping("questions")
	public ResponseEntity<QuestionDto> addQuestion(@RequestBody @Valid QuestionDto questionDto)
			throws QuestionException {
		log.info("QuestionApi:Add Question..");
		return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.CREATED);
	}

	@DeleteMapping("questions/{id}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable("id") int questionId) {
		log.info("QuestionApi:delete Questions..");
		questionService.removeQuestion(questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("questions")
	public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Valid QuestionDto questionDto)
			throws QuestionException {
		log.info("QuestionApi:update Questions..");
		return new ResponseEntity<>(questionService.updateQuestion(questionDto), HttpStatus.ACCEPTED);
	}
}
