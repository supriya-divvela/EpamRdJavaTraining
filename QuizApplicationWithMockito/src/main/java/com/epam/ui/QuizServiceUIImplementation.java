package com.epam.ui;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Quiz;
import com.epam.service.QuestionService;
import com.epam.service.QuestionServiceImplementation;
import com.epam.service.QuizService;
import com.epam.service.QuizServiceImplementation;

public class QuizServiceUIImplementation implements QuizServiceUI {
	private Logger logger = (Logger) LogManager.getLogger("QuizFunctionalitiesImplementation.class");
	private QuestionService questionService = new QuestionServiceImplementation();
	private QuizService quizService = new QuizServiceImplementation();

	@Override
	public void createQuiz(int quizNo) {
		Quiz quiz = new Quiz();
		logger.info("Enter title for quiz: ");
		String title = Input.read();
		logger.info("enter number of questions to add: ");
		int noOfQuestions = Input.readInt();
		try {
			questionService.displayQuestions();
			logger.info("Enter {} questions numbers:",noOfQuestions);
			for (int i = 0; i < noOfQuestions; i++) {
				logger.info("Enter Question Number:");
				int qNo = Input.readInt();
				if (questionService.findQuestion(qNo) && !quizService.isQuestionPresentInQuiz(quiz, qNo)) {
					quiz.getListOfQuestions().add(questionService.getQuestion(qNo));
				}
			}
			quiz = new Quiz(quizNo, title, quiz.getListOfQuestions());
			quizService.addQuiz(quiz);
		} catch (DuplicateQuizNumberException | EmptyQuestionLibraryException
				| QuestionNotFoundException e) {
			logger.info(e);
		}
	}

	@Override
	public void updateQuiz(int quizNo) {
		try {
			logger.info("Enter value to proceed..\n1)Add a question\n2)Remove a question\n3)Exit");
			int choice = Input.readInt();
			do {
				if (choice == 3) {
					break;
				}
				UiOperations.getUpdateQuiz().get(choice).accept(quizNo);
				logger.info("Enter value to proceed..\n1)Add a question\n2)Remove a question\n3)Exit");
				choice = Input.readInt();
			} while (choice != 3);
		} catch (InputMismatchException e) {
			logger.info("Please enter numbers only..");
		}
	}
}
