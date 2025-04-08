package com.epam.ui;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.epam.model.Question;
import com.epam.service.QuestionService;
import com.epam.service.QuestionServiceImplementation;

public class QuestionFunctionalitiesImplementation implements QuestionFunctionalities {
	private Logger logger = (Logger) LogManager.getLogger("QuestionFunctionalitiesImplementation.class");
	private Scanner scanner = new Scanner(System.in);
	private QuestionService questionService = new QuestionServiceImplementation();

	@Override
	public void createQuestion(int qNo) {
		try {
			logger.info("Enter title of the question : ");
			String title = scanner.nextLine();
			logger.info("Enter number of options you want to give :");
			int numberOfOptions = Integer.parseInt(scanner.nextLine());
			Set<String> options = new TreeSet<String>();
			for (int i = 1; i <= numberOfOptions; i++) {
				logger.info("Enter option " + i + " :");
				options.add(scanner.nextLine());
			}
			logger.info("Enter difficulty :");
			String difficulty = scanner.nextLine();
			logger.info("Enter tagging topic");
			String taggingTopic = scanner.nextLine();
			logger.info("Enter number of options as answers :");
			int numberOfAnswers = Integer.parseInt(scanner.nextLine());
			Set<Integer> answers = new TreeSet<Integer>();
			for (int i = 1; i <= numberOfAnswers; i++) {
				int answer = Integer.parseInt(scanner.nextLine());
				do {
					logger.info("Enter option " + i + " in range(1," + numberOfOptions + ") :");
					if (answer <= numberOfOptions && answer >= 1) {
						answers.add(answer);
						break;
					}
					answers.add(answer);
					answer = Integer.parseInt(scanner.nextLine());
				} while (answer > numberOfOptions && answer < 1);
			}
			questionService.addQuestion(new Question(qNo, title, options, difficulty, taggingTopic, answers));
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	@Override
	public void updateQuestion(int qNo) {
		try {
			logger.info(
					"Enter What do you want to update:\n1)Title\n2)Options\n3)Difficulty\n4)Tagging topic\n5)Answer\n6)Exit\n");
			int choice = Integer.parseInt(scanner.nextLine());
			do {
				if (choice == 6) {
					break;
				}
				UiOperations.getUpdateQuestion().get(choice);
				logger.info(
						"Enter What do you want to update:\n1)Title\n2)Options\n3)Difficulty\n4)Tagging topic\n5)Answer\n9)Exit\n");
				choice = Integer.parseInt(scanner.nextLine());
			} while (choice != 6);
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}
}
