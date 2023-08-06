package com.epam.exception;

public class QuizNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public QuizNotFoundException() {
		super("Quiz not found with this quiz number...");
	}
}
