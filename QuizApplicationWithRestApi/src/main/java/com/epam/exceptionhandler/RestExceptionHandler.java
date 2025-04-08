package com.epam.exceptionhandler;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.epam.exception.QuestionException;
import com.epam.exception.QuizException;
import com.epam.exception.UserException;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Component
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {
		log.error("RestExceptionHandler:handleMethodArgumentNotValidException");
		return new ExceptionResponse(
				new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getAllErrors().stream()
						.map(error -> error.getDefaultMessage()).reduce("", (a, b) -> a + "\n" + b),
				request.getDescription(false));
	}

	@ExceptionHandler(QuestionException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleQuestionException(QuestionException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleQuestionException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException exception,
			WebRequest request) {
		log.error("RestExceptionHandler:handleDataIntegrityViolationException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(QuizException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleQuizNotFoundException(QuizException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleQuizNotFoundException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleEntityNotFoundException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.NOT_FOUND.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception,
			WebRequest request) {
		log.error("RestExceptionHandler:handleMethodArgumentTypeMismatchException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleDuplicateUserFoundException(UserException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleDuplicateUserFoundException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleRuntimeException(RuntimeException e, WebRequest request) {
		log.error("RestExceptionHandler:handleRuntimeException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				request.getDescription(false));
	}
}
