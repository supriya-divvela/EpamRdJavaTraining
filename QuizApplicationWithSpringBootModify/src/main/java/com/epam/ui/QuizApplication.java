package com.epam.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizApplication {
	private static Logger logger = LogManager.getLogger("QuizApplication.class");
	@Autowired
	private UserUI userUI;

	public void start() {
		String options = "Enter number to proceed:\n1)Press 1 for user\n2)Press 2 for admin\n3)Press 3 for exit";
		logger.info(options);
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
			logger.info(options);
			usertype = Input.readInt();
		} while (usertype != 3);
	}
}
