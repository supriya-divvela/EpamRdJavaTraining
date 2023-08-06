package com.epam.exception;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.model.ExceptionResponse;

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

	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse handleQuestionException(UserException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleUserException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.NOT_FOUND.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleDataIntegrityViolationException(DataIntegrityViolationException exception,
			WebRequest request) {
		log.info("RestExceptionHandler:handleDataIntegrityViolationException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.name(),
				"Cannot add user as same user with that name already exists", request.getDescription(false));
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleRuntimeException(RuntimeException e, WebRequest request) {
		log.error("UserRestExceptionHandler:handleRuntimeException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				request.getDescription(false));
	}
}
