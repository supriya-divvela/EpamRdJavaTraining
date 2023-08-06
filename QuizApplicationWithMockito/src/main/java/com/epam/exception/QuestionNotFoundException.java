package com.epam.exception;

public class QuestionNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public QuestionNotFoundException() {
		super("Question not found with this question number...");
	}
}
