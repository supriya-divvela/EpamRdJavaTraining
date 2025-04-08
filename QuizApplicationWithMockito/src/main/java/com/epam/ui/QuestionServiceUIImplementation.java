package com.epam.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.model.Question;
import com.epam.service.QuestionService;
import com.epam.service.QuestionServiceImplementation;

public class QuestionServiceUIImplementation implements QuestionServiceUI {
	private Logger logger = (Logger) LogManager.getLogger("QuestionFunctionalitiesImplementation.class");
	private QuestionService questionService = new QuestionServiceImplementation();

	@Override
	public void createQuestion(int qNo) {
		logger.info("Enter title of the question : ");
		String title = Input.read();
		logger.info("Enter number of options you want to give :");
		int numberOfOptions = Input.readInt();
		List<String> options = new ArrayList<>();
		for (int i = 1; i <= numberOfOptions; i++) {
			logger.info("Enter option {} :", i);
			options.add(Input.read());
		}
		logger.info("Enter difficulty :");
		String difficulty = Input.read();
		logger.info("Enter tagging topic");
		String taggingTopic = Input.read();
		logger.info("Enter number of options as answers :");
		int numberOfAnswers = Input.readInt();
		Set<Integer> answers = new TreeSet<>();
		for (int i = 1; i <= numberOfAnswers; i++) {
			int answer = 0;
			do {
				logger.info("Enter option {} in range(1,{})+", i, numberOfOptions);
				answer = Input.readInt();
				if (answer <= numberOfOptions && answer >= 1) {
					answers.add(answer);
					break;
				}
			} while (answer > numberOfOptions || answer < 1);
		}
		logger.info("Enter marks:");
		int marks = Input.readInt();
		try {
			questionService.addQuestion(new Question(qNo, title, options, difficulty, taggingTopic, answers, marks));
		} catch (DuplicateQuestionNumberException e) {
			logger.info(e);
		}
	}

	@Override
	public void updateQuestion(int qNo) {
		try {
			logger.info(
					"Enter What do you want to update:\n1)Title\n2)Options\n3)Difficulty\n4)Tagging topic\n5)Answer\n6)Marks\n7)Exit\n");
			int choice = Integer.parseInt(Input.read());
			do {
				if (choice == 7) {
					break;
				}
				UiOperations.getUpdateQuestion().get(choice).accept(qNo);
				logger.info(
						"Enter What do you want to update:\n1)Title\n2)Options\n3)Difficulty\n4)Tagging topic\n5)Answer\n6)Marks\n7)Exit\n");
				choice = Input.readInt();
			} while (choice != 7);
		} catch (InputMismatchException e) {
			logger.info("Please Enter numbers only..");
		}
	}
}
