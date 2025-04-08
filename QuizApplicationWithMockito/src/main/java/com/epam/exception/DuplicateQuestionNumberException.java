package com.epam.exception;

public class DuplicateQuestionNumberException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateQuestionNumberException() {
		super("Duplicate Question Number Exception...");
	}
}
