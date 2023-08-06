package com.epam.rd;

public class InvalidAccountNumber extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAccountNumber(String message) {
		super(message);
	}

	public InvalidAccountNumber() {

	}

}
