package com.epam.exception;

public class DuplicateUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateUserException() {
		super("Duplicate User Found Exception...");
	}
}
