package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class AdminFunctionalities {
	private Logger logger = (Logger) LogManager.getLogger("AdminFunctionalities.class");
	private Scanner scanner = new Scanner(System.in);

	public void questionsAccess() {
		try {
			logger.info("Enter number to proceed:\n" + "1)Create Question\n" + "2)Delete Question\n"
					+ "3)Update Question\n" + "4)Display All questions\n" + "5)Exit");
			int choice = Integer.parseInt(scanner.nextLine());
			do {
				if (choice == 5) {
					break;
				}
				if (UiOperations.getQuestionAccess().get(choice) == null) {
					logger.info("Enter proper number..");
				} else {
					UiOperations.getQuestionAccess().get(choice).run();
				}
				logger.info("Enter number to proceed:\n" + "1)Create Question\n" + "2)Delete Question\n"
						+ "3)Update Question\n" + "4)Display All questions\n" + "5)Exit");
				choice = Integer.parseInt(scanner.nextLine());
			} while (choice != 5);
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	public void quizAccess() {
		try {
			logger.info("Enter number to proceed:\n" + "1)Create Quiz\n" + "2)Delete Quiz\n" + "3)Update Quiz\n"
					+ "4)Display All available quizes\n" + "5)Exit");
			int choice = Integer.parseInt(scanner.nextLine());
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
				choice = Integer.parseInt(scanner.nextLine());
			} while (choice != 5);
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}
}
