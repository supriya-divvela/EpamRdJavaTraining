package com.epam.exception;

public class QuestionNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public QuestionNotFoundException(String message) {
		super(message);
	}
}
