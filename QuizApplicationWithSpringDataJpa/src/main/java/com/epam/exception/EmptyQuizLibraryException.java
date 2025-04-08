package com.epam.exception;

public class EmptyQuizLibraryException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyQuizLibraryException() {
		super("Quiz Library is Empty...");
	}
}
