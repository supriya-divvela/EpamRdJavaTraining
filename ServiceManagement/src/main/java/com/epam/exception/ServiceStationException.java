package com.epam.exception;

public class ServiceStationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ServiceStationException(String message) {
		super(message);
	}
}
