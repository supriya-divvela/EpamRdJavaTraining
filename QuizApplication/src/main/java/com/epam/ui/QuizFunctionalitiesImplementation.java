package com.epam.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuestionService;
import com.epam.service.QuestionServiceImplementation;
import com.epam.service.QuizService;
import com.epam.service.QuizServiceImplementation;

public class QuizFunctionalitiesImplementation implements QuizFunctionalities {
	private Logger logger = (Logger) LogManager.getLogger("QuizFunctionalitiesImplementation.class");
	private Scanner scanner = new Scanner(System.in);
	private QuestionService questionService = new QuestionServiceImplementation();
	private QuizService quizService = new QuizServiceImplementation();

	@Override
	public void createQuiz(int quizNo) {
		Map<Question, Integer> listOfQuestions = new HashMap<>();
		int totalMarks;
		logger.info("Enter title for quiz: ");
		String title = scanner.nextLine();
		logger.info("enter number of questions to add: ");
		try {
			int noOfQuestions = Integer.parseInt(scanner.nextLine());
			questionService.displayQuestions();
			logger.info("Enter " + noOfQuestions + " questions numbers:");
			for (int i = 0; i < noOfQuestions; i++) {
				logger.info("Enter Question Number:");
				int qNo = Integer.parseInt(scanner.nextLine());
				logger.info("Enter Marks for this question");
				int marks = Integer.parseInt(scanner.nextLine());
				if (questionService.findQuestion(qNo)) {
					listOfQuestions.put(questionService.getQuestion(qNo), marks);
				}
			}
		} catch (NumberFormatException e) {
			logger.info("Please enter numbers only..");
		}
		logger.info("Enter total marks :");
		totalMarks = listOfQuestions.entrySet().stream().mapToInt(question -> question.getValue()).sum();
		Quiz quiz = new Quiz(quizNo, title, listOfQuestions, totalMarks);
		quizService.addQuiz(quiz);
	}

	@Override
	public void updateQuiz(int quizNo) {
		logger.info("Enter value to proceed..\n1)Add a question\n2)Remove a question\n3)Exit");
		logger.info(questionService.displayQuestions());
		try {
			int choice = Integer.parseInt(scanner.nextLine());
			do {
				if (choice == 3) {
					break;
				}
				UiOperations.getUpdateQuiz().get(choice);
				logger.info("Enter value to proceed..\n1)Add a question\n2)Remove a question\n3)Exit");
				logger.info(questionService.displayQuestions());
				choice = Integer.parseInt(scanner.nextLine());
			} while (choice != 3);
		} catch (NumberFormatException e) {
			logger.info("Please enter numbers only..");
		}
	}
}
