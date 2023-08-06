package com.epam;

/*
 * In Exceptions everything is a class.
 * we can create our own exceptions that are derived classes of the Throwable class or itself.
 * It is highly recommended to use throw keyword for user defined exceptions.
 * Whenever we try to extend user defined exceptions preferable to use RuntimeException as it is unchecked exception.
 * Creating our own Exception is known as custom exception or user-defined exception.
 * */

@SuppressWarnings("serial")
public class UserdefinedExceptions extends RuntimeException {
	UserdefinedExceptions(String msg){
		super(msg);
	}
	public static void checkEligibility(int age) {
		if(age<18) {
			throw new UserdefinedExceptions("Ineligible to vote");
		}
		else {
			System.out.println("Eligible to vote");
		}
	}
	public static void main(String[] args){
		try {
			checkEligibility(10);
		}
		catch(UserdefinedExceptions e) {
			System.out.println(e.getMessage());	
		}
	}
}
