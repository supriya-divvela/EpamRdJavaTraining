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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuizDto;
import com.epam.exception.QuestionException;
import com.epam.exception.QuizException;
import com.epam.service.QuizService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("quizspage")
@Slf4j
public class QuizApi {
	@Autowired
	private QuizService quizService;

	@GetMapping("quizs")
	public ResponseEntity<List<QuizDto>> getQuizs() {
		log.info("QuizApi:Get all quizes...");
		return new ResponseEntity<>(quizService.displayAvaliableQuizes(), HttpStatus.OK);
	}

	@GetMapping("quizs/{id}")
	public ResponseEntity<QuizDto> getQuiz(@PathVariable("id") int quizId) throws QuizException {
		log.info("QuizApi:Get one certain quiz...");
		return new ResponseEntity<>(quizService.getQuiz(quizId), HttpStatus.OK);
	}

	@PostMapping("quizs")
	public ResponseEntity<QuizDto> addQuiz(@RequestParam @NotBlank(message = "Title is not valid..") String title,@RequestParam List<Integer> listOfQuestions) throws QuestionException {
		log.info("QuizApi:Add quiz...");
		return new ResponseEntity<>(quizService.addQuiz(quizService.createQuiz(title, listOfQuestions)), HttpStatus.CREATED);
	}

	@DeleteMapping("quizs/{id}")
	public ResponseEntity<Void> deleteQuiz(@PathVariable("id") int quizId) throws QuizException {
		log.info("QuizApi:delete quiz...");
		quizService.removeQuiz(quizId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("quizs")
	public ResponseEntity<QuizDto> updateQuiz(@RequestBody @Valid QuizDto quizDto) throws QuizException {
		log.info("QuizApi:update quiz...");
		return new ResponseEntity<>(quizService.updateQuiz(quizDto), HttpStatus.ACCEPTED);
	}

}
