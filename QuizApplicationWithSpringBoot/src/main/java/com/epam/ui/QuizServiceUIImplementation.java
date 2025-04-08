package com.epam.ui;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;

@Component
public class QuizServiceUIImplementation implements QuizServiceUI {
	private Logger logger = LogManager.getLogger("QuizServiceUIImplementation.class");
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuizService quizService;
	Map<Integer, Runnable> quizAccess = new HashMap<>();
	Map<Integer, Consumer<Integer>> updateQuiz = new HashMap<>();
	private static String enterQuizNumber = "Enter quiz number:";
	private static String enterQuestionNumber = "Enter question number:";

	@Override
	public void createQuiz(int quizNo) {
		Quiz quiz = new Quiz();
		logger.info("Enter title for quiz: ");
		String title = Input.read();
		logger.info("enter number of questions to add: ");
		int noOfQuestions = Input.readInt();
		try {
			questionService.displayQuestions();
			logger.info("Enter {} questions numbers:", noOfQuestions);
			for (int i = 0; i < noOfQuestions; i++) {
				logger.info("Enter Question Number:");
				int qNo = Input.readInt();
				if (questionService.findQuestion(qNo) && !quizService.isQuestionPresentInQuiz(quiz, qNo)) {
					quiz.getListOfQuestions().add(questionService.getQuestion(qNo));
				}
			}
			quiz = new Quiz(quizNo, title, quiz.getListOfQuestions());
			quizService.addQuiz(quiz);
		} catch (DuplicateQuizNumberException | EmptyQuestionLibraryException | QuestionNotFoundException e) {
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
				getUpdateQuiz().get(choice).accept(quizNo);
				logger.info("Enter value to proceed..\n1)Add a question\n2)Remove a question\n3)Exit");
				choice = Input.readInt();
			} while (choice != 3);
		} catch (InputMismatchException e) {
			logger.info("Please enter numbers only..");
		}
	}

	@Override
	public Map<Integer, Runnable> getQuizAccess() {
		quizAccess.put(1, () -> {
			logger.info(enterQuizNumber);
			int quizNo = Input.readInt();
			try {
				if (!quizService.findQuiz(quizNo)) {
					createQuiz(quizNo);
					logger.info("Quiz Added Successfully..");
				} else {
					throw new DuplicateQuizNumberException();
				}
			} catch (DuplicateQuizNumberException | QuizNotFoundException e) {
				logger.info(e);
			}
		});
		quizAccess.put(2, () -> {
			logger.info(enterQuizNumber);
			int quizNo = Input.readInt();
			try {
				if (quizService.findQuiz(quizNo)) {
					quizService.removeQuiz(quizService.getQuiz(quizNo));
					logger.info("Quiz Removed Successfully..");
				} else {
					throw new QuizNotFoundException();
				}
			} catch (QuizNotFoundException e) {
				logger.info(e);
			}
		});
		quizAccess.put(3, () -> {
			logger.info(enterQuizNumber);
			int quizNo = Input.readInt();
			try {
				if (quizService.findQuiz(quizNo)) {
					updateQuiz(quizNo);
					logger.info("Updated Succesfully..");
				} else {
					throw new QuizNotFoundException();
				}
			} catch (QuizNotFoundException e) {
				logger.info(e);
			}
		});
		quizAccess.put(4, () -> {
			if (!quizService.isEmptyQuizLibrary()) {
				logger.info(quizService.displayAvaliableQuizes());
			} else {
				logger.info("No quizes in the library...");
			}

		});
		return quizAccess;
	}

	@Override
	public Map<Integer, Consumer<Integer>> getUpdateQuiz() {
		updateQuiz.put(1, quizNo -> {
			Quiz quiz = null;
			try {
				quiz = quizService.getQuiz(quizNo);
				if (!quiz.getListOfQuestions().isEmpty()) {
					logger.info("All Available Questions ins quiz are :");
					quiz.getListOfQuestions().stream().map(Question::getQNo).forEach(qNo -> logger.info(qNo));
				} else {
					throw new QuestionNotFoundException();
				}
				logger.info(enterQuestionNumber);
				int qNo = Input.readInt();
				if (questionService.findQuestion(qNo)) {
					if (!quizService.isQuestionPresentInQuiz(quiz, qNo)) {
						quizService.addQuestionToQuiz(quiz, questionService.getQuestion(qNo));
						logger.info("Question added successful...");
					} else {
						throw new DuplicateQuestionNumberException();
					}
				} else {
					throw new QuestionNotFoundException();
				}
			} catch (QuestionNotFoundException | DuplicateQuestionNumberException | QuizNotFoundException e) {
				logger.info(e);
			}
		});
		updateQuiz.put(2, quizNo -> {
			Quiz quiz = null;
			try {
				quiz = quizService.getQuiz(quizNo);
				if (!quiz.getListOfQuestions().isEmpty()) {
					logger.info("All Available Questions ins quiz are :");
					quiz.getListOfQuestions().stream().map(Question::getQNo).forEach(qNo -> logger.info(qNo));
				} else {
					throw new QuestionNotFoundException();
				}
				logger.info("Enter question number to remove from quiz..");
				int qNo = Input.readInt();
				if (quizService.isQuestionPresentInQuiz(quiz, qNo)) {
					quizService.removeQuestionFromQuiz(quiz, questionService.getQuestion(qNo));
					logger.info("Question Removed Succesfully..");
				} else {
					throw new QuestionNotFoundException();
				}
			} catch (QuizNotFoundException | QuestionNotFoundException e) {
				logger.info(e);
			}
		});
		return updateQuiz;
	}
}
