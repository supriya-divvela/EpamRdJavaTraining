package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class QuizApplication {
	private static Logger logger = (Logger) LogManager.getLogger("QuizApplication.class");
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			logger.info("Enter number to proceed:\n" + "1)Press 1 for user\n2)Press 2 for admin\n" + "2)Press 3 for exit");
			int usertype = Integer.parseInt(scanner.nextLine());
			do {
				if (usertype == 3) {
					break;
				}
				if (UiOperations.getQuizApplication().get(usertype) == null) {
					logger.info("Enter proper number..");
				} else {
					UiOperations.getQuizApplication().get(usertype).run();
				}
				logger.info("Enter number to proceed:\n" + "1)Press 1 for user\n2)Press 2 for admin\n" + "2)Press 3 for exit");
				usertype = Integer.parseInt(scanner.nextLine());
			} while (usertype != 3);
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}
}
