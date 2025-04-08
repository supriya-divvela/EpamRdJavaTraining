package com.epam.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class QuizApplication {
	private static Logger logger = (Logger) LogManager.getLogger("QuizApplication.class");
	private static UserUI userUI = new UserUI();

	private QuizApplication() {
	}

	public static void start() {
		logger.info("Enter number to proceed:\n" + "1)Press 1 for user\n2)Press 2 for admin\n" + "3)Press 3 for exit");
		int usertype = Input.readInt();
		do {
			if (usertype == 1) {
				userUI.userOperations();
			} else if (usertype == 2) {
				userUI.adminOperations();
			} else if (usertype == 3) {
				break;
			} else {
				logger.info("Enter proper number..");
			}
			logger.info(
					"Enter number to proceed:\n" + "1)Press 1 for user\n2)Press 2 for admin\n" + "2)Press 3 for exit");
			usertype = Input.readInt();
		} while (usertype != 3);
	}
}
