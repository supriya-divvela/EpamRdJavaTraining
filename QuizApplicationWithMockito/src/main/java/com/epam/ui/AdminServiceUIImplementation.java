package com.epam.ui;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class AdminServiceUIImplementation implements AdminServiceUI {
	private Logger logger = (Logger) LogManager.getLogger("AdminFunctionalities.class");

	public void questionsAccess() {
		String variousOptions="Enter number to proceed:\n1)Create Question\n2)Delete Question\n3)Update Question\n4)Display All questions\n5)Exit";
		try {
			logger.info(variousOptions);
			int choice = Input.readInt();
			do {
				if (choice == 5) {
					break;
				}
				if (UiOperations.getQuestionAccess().get(choice) == null) {
					logger.info("Enter proper number..");
				} else {
					UiOperations.getQuestionAccess().get(choice).run();
				}
				logger.info(variousOptions);
				choice = Input.readInt();
			} while (choice != 5);
		} catch (InputMismatchException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	public void quizAccess() {
		try {
			logger.info("Enter number to proceed:\n" + "1)Create Quiz\n" + "2)Delete Quiz\n" + "3)Update Quiz\n"
					+ "4)Display All available quizes\n" + "5)Exit");
			int choice = Input.readInt();
			do {
				if (choice == 5) {
					break;
				}
				if (UiOperations.getQuizAccess().get(choice) == null) {
					logger.info("Enter proper number..");
				} else {
					UiOperations.getQuizAccess().get(choice).run();
				}
				logger.info("Enter number to proceed:\n" + "1)Create Quiz\n" + "2)Delete Quiz\n" + "3)Update Quiz\n"
						+ "4)Display All available quizes\n" + "5)Exit");
				choice = Input.readInt();
			} while (choice != 5);
		} catch (InputMismatchException e) {
			logger.info("Please Enter numbers only..");
		}
	}
}
