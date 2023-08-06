package com.epam.exception;

public class EmptyQuestionLibraryException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyQuestionLibraryException(){
		super("Question Library is Empty...");
	}

}
