package com.epam.ui;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceUIImplementation implements AdminServiceUI {
	private Logger logger = LogManager.getLogger("AdminServiceUIImplementation.class");
	@Autowired
	private QuizServiceUI quizServiceUI;
	@Autowired
	private QuestionServiceUI questionServiceUI;

	public void questionsAccess() {
		String variousOptions = "Enter number to proceed:\n1)Create Question\n2)Delete Question\n3)Update Question\n4)Display All questions\n5)Exit";
		try {
			logger.info(variousOptions);
			int choice = Input.readInt();
			do {
				if (choice == 5) {
					break;
				}
				if (questionServiceUI.getQuestionAccess().get(choice) == null) {
					logger.info("Enter proper number..");
				} else {
					questionServiceUI.getQuestionAccess().get(choice).run();
				}
				logger.info(variousOptions);
				choice = Input.readInt();
			} while (choice != 5);
		} catch (InputMismatchException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	public void quizAccess() {
		String options = "Enter number to proceed:\n1)Create Quiz\n2)Delete Quiz\n3)Update Quiz\n4)Display All available quizes\n5)Exit";
		try {
			logger.info(options);
			int choice = Input.readInt();
			do {
				if (choice == 5) {
					break;
				}
				if (quizServiceUI.getQuizAccess().get(choice) == null) {
					logger.info("Enter proper number..");
				} else {
					quizServiceUI.getQuizAccess().get(choice).run();
				}
				logger.info(options);
				choice = Input.readInt();
			} while (choice != 5);
		} catch (InputMismatchException e) {
			logger.info("Please Enter numbers only..");
		}
	}
}
