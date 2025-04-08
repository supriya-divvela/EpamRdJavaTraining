package com.epam.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.epam.model.ExceptionResponse;

import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Component
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {
		log.error("LibraryRestExceptionHandler:handleMethodArgumentNotValidException");
		return new ExceptionResponse(
				new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getAllErrors().stream()
						.map(error -> error.getDefaultMessage()).reduce("", (a, b) -> a + "\n" + b),
				request.getDescription(false));
	}

	@ExceptionHandler(LibraryException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse handleLibraryException(LibraryException exception, WebRequest request) {
		log.error("LibraryRestExceptionHandler:handleLibraryException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.NOT_FOUND.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception,
			WebRequest request) {
		log.info("LibraryRestExceptionHandler:handleMethodArgumentTypeMismatchException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleRuntimeException(RuntimeException e, WebRequest request) {
		log.error("LibraryRestExceptionHandler:handleRuntimeException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				request.getDescription(false));
	}
	
	@ExceptionHandler(FeignClientException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleFeignClientException(FeignClientException e, WebRequest request) {
		log.error("LibraryRestExceptionHandler:handleFeignClinetException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				request.getDescription(false));
	}
	
}
