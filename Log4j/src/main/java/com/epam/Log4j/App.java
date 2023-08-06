package com.epam.Log4j;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	private static final Logger logger = LogManager.getLogger("App.class");

	public static void main(String[] args) throws IOException {
		
		logger.debug("Hello world");
		logger.info("Hello world");
		logger.warn("Hello world");
		logger.fatal("Hello world");

	}
}
