package com.epam.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuestionService;
import com.epam.service.QuestionServiceImplementation;
import com.epam.service.QuizService;
import com.epam.service.QuizServiceImplementation;

public class UiOperations {
	private static Map<Integer, Runnable> quizApplication = new HashMap<Integer, Runnable>();
	private static Map<Integer, Runnable> adminLogin = new HashMap<Integer, Runnable>();
	private static Map<Integer, Runnable> adminOperations = new HashMap<Integer, Runnable>();
	private static Map<Integer, Runnable> quizAccess = new HashMap<Integer, Runnable>();
	private static Map<Integer, Runnable> questionAccess = new HashMap<Integer, Runnable>();
	private static Map<Integer, Runnable> userLogin = new HashMap<Integer, Runnable>();
	private static Map<Integer, Runnable> userOperations = new HashMap<Integer, Runnable>();
	private static Map<Integer, Consumer<Integer>> updateQuestion = new HashMap<Integer, Consumer<Integer>>();
	private static Map<Integer, Consumer<Integer>> updateQuiz = new HashMap<Integer, Consumer<Integer>>();
	private static Logger logger = (Logger) LogManager.getLogger("UiOperations.class");
	private static Scanner scanner = new Scanner(System.in);
	private static QuestionService questionService = new QuestionServiceImplementation();
	private static QuizService quizService = new QuizServiceImplementation();
	private static AdminFunctionalities adminFunctionalities = new AdminFunctionalities();
	private static UserUI userUI = new UserUI();
	private static QuizFunctionalitiesImplementation quizFunctionalitiesImplementation = new QuizFunctionalitiesImplementation();
	private static QuestionFunctionalitiesImplementation questionFunctionalitiesImplementation = new QuestionFunctionalitiesImplementation();
	static {
		// Quiz Application Main Option
		quizApplication.put(1, () -> userUI.userOperations());
		quizApplication.put(2, () -> userUI.adminOperations());

		// Admin Login Options
		adminLogin.put(1, () -> adminFunctionalities.questionsAccess());
		adminLogin.put(2, () -> adminFunctionalities.quizAccess());
		adminLogin.put(3, () -> userUI.userRegister());

		// Admin Operation Options
		adminOperations.put(1, () -> userUI.userLogin());

		// Quiz Access Options
		quizAccess.put(1, () -> {
			logger.info("Enter quiz number:");
			int quizNo = Integer.parseInt(scanner.nextLine());
			if (!quizService.findQuiz(quizNo)) {
				quizFunctionalitiesImplementation.createQuiz(quizNo);
				logger.info("Quiz Added Successfully..");
			} else {
				try {
					throw new DuplicateQuizNumberException();
				} catch (DuplicateQuizNumberException e) {
					logger.info(e.toString());
				}
			}
		});
		quizAccess.put(2, () -> {
			try {
				logger.info("Enter quiz number:");
				int quizNo = Integer.parseInt(scanner.nextLine());
				if (quizService.findQuiz(quizNo)) {
					quizService.removeQuiz(quizService.getQuiz(quizNo));
					logger.info("Quiz Removed Successfully..");
				} else {
					try {
						throw new QuizNotFoundException();
					} catch (QuizNotFoundException e) {
						logger.info(e.toString());
					}
				}
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only..");
			}
		});
		quizAccess.put(3, () -> {
			try {
				logger.info("Enter quiz number:");
				int quizNo = Integer.parseInt(scanner.nextLine());
				if (quizService.findQuiz(quizNo)) {
					quizFunctionalitiesImplementation.updateQuiz(quizNo);
					logger.info("Updated Succesfully..");
				} else {
					try {
						throw new QuizNotFoundException();
					} catch (QuizNotFoundException e) {
						logger.info(e.toString());
					}
				}
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only..");
			}
		});
		quizAccess.put(4, () -> {
			if (!quizService.isEmptyQuizLibrary()) {
				logger.info(quizService.displayAvaliableQuizes());
			} else {
				try {
					throw new EmptyQuestionLibraryException();
				} catch (EmptyQuestionLibraryException e) {
					logger.info(e.toString());
				}
			}
		});
		// Question Access Options
		questionAccess.put(1, () -> {
			try {
				logger.info("Enter quiz number:");
				int quizNo = Integer.parseInt(scanner.nextLine());
				if (!quizService.findQuiz(quizNo)) {
					quizFunctionalitiesImplementation.createQuiz(quizNo);
					logger.info("Quiz Added Successfully..");
				} else {
					try {
						throw new DuplicateQuizNumberException();
					} catch (DuplicateQuizNumberException e) {
						logger.info(e.toString());
					}
				}
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only..");
			}
		});
		questionAccess.put(2, () -> {
			try {
				logger.info("Enter Question Number :");
				int qNo = Integer.parseInt(scanner.nextLine());
				if (questionService.findQuestion(qNo)) {
					questionService.removeQuestion(questionService.getQuestion(qNo));
					logger.info("Removed succesfully");
				} else {
					try {
						throw new QuestionNotFoundException();
					} catch (QuestionNotFoundException e) {
						logger.info(e.toString());
					}
				}
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only..");
			}
		});
		questionAccess.put(3, () -> {
			try {
				logger.info("Enter Question Number :");
				int qNo = Integer.parseInt(scanner.nextLine());
				if (questionService.findQuestion(qNo)) {
					questionFunctionalitiesImplementation.updateQuestion(qNo);
					logger.info("Updated Succesfully");
				} else {
					try {
						throw new QuestionNotFoundException();
					} catch (QuestionNotFoundException e) {
						logger.info(e.toString());
					}
				}
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only..");
			}
		});
		questionAccess.put(4, () -> {
			if (!questionService.isEmptyQuestionLibrary()) {
				logger.info(questionService.displayQuestions());
			} else {
				try {
					throw new EmptyQuestionLibraryException();
				} catch (EmptyQuestionLibraryException e) {
					logger.info(e.toString());
				}
			}
		});

		// User Login Options
		userLogin.put(1, () -> {
			userUI.attemptQuiz();
		});
		userLogin.put(2, () -> {
			logger.info("All Available quizes are:");
			logger.info(quizService.displayAvaliableQuizes());
		});

		// User Operations
		userOperations.put(1, () -> {
			userUI.userLogin();
		});
		userOperations.put(2, () -> {
			userUI.userRegister();
		});

		// update Question
		updateQuestion.put(1, (qNo) -> {
			logger.info("Enter title of the question : ");
			String title = scanner.nextLine();
			questionService.updateTitle(qNo, title);
		});
		updateQuestion.put(2, (qNo) -> {
			logger.info("Enter Options size :");
			int numberOfOptions = Integer.parseInt(scanner.nextLine());
			Set<String> options = new TreeSet<String>();
			for (int i = 1; i <= numberOfOptions; i++) {
				logger.info("Enter option " + i + " :");
				options.add(scanner.nextLine());
			}
			questionService.updateOptions(qNo, options);
		});
		updateQuestion.put(3, (qNo) -> {
			logger.info("Enter difficulty :");
			String difficulty = scanner.nextLine();
			questionService.updateDifficulty(qNo, difficulty);
		});
		updateQuestion.put(4, (qNo) -> {
			logger.info("Enter tagging topic");
			String taggingTopic = scanner.nextLine();
			questionService.updateTaggingTopic(qNo, taggingTopic);
		});
		updateQuestion.put(5, (qNo) -> {
			Question question = questionService.getQuestion(qNo);
			int numberOfOptions = question.getOptions().size();
			Set<Integer> newAnswers = new TreeSet<Integer>();
			logger.info("The answers for this question in the range(1," + numberOfOptions + ") :");
			int numberOfAnswersUpdate = Integer.parseInt(scanner.nextLine());
			do {
				for (int i = 1; i <= numberOfAnswersUpdate; i++) {
					logger.info("Enter answer to update :");
					int optionUpdate = Integer.parseInt(scanner.nextLine());
					do {

						if (optionUpdate <= numberOfOptions && numberOfOptions >= 1) {
							newAnswers.add(optionUpdate);
							break;
						}
						logger.info("Enter answer to update :");
						optionUpdate = Integer.parseInt(scanner.nextLine());
					} while (optionUpdate > numberOfOptions && optionUpdate < 1);
				}
				questionService.updateAnswers(qNo, newAnswers);
				logger.info("The answers for this question in the range(1," + numberOfOptions + ") :");
				numberOfAnswersUpdate = Integer.parseInt(scanner.nextLine());
			} while (numberOfAnswersUpdate > numberOfOptions);
		});

		// Update Quiz
		updateQuiz.put(1, (quizNo) -> {
			try {
				Quiz quiz = quizService.getQuiz(quizNo);
				logger.info("Enter Question Number:");
				int qNo = Integer.parseInt(scanner.nextLine());
				if (questionService.findQuestion(qNo)) {
					if (!quizService.isQuestionPresentInQuiz(quiz, qNo)) {
						logger.info("Enter Marks for this question");
						int marks = Integer.parseInt(scanner.nextLine());
						quizService.addQuestionToQuiz(quiz, questionService.getQuestion(qNo), marks);
						logger.info("Question added successful...");
					} else {
						try {
							throw new DuplicateQuestionNumberException();
						} catch (DuplicateQuestionNumberException e) {
							logger.info(e.toString());
						}
					}
				} else {
					try {
						throw new QuestionNotFoundException();
					} catch (QuestionNotFoundException e) {
						logger.info(e.toString());
					}
				}
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only..");
			}
		});
		updateQuiz.put(2, (quizNo) -> {
			try {
				Quiz quiz = quizService.getQuiz(quizNo);
				logger.info("Enter question number to remove from quiz..");
				int qNo = Integer.parseInt(scanner.nextLine());
				if (quizService.isQuestionPresentInQuiz(quiz, qNo)) {
					quizService.removeQuestionFromQuiz(quiz, questionService.getQuestion(qNo));
					logger.info("Question Removed Succesfully..");
				} else {
					try {
						throw new QuestionNotFoundException();
					} catch (QuestionNotFoundException e) {
						logger.info(e.toString());
					}
				}
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only..");
			}
		});
	}

	public static Map<Integer, Runnable> getQuizApplication() {
		return quizApplication;
	}

	public static Map<Integer, Runnable> getAdminLogin() {
		return adminLogin;
	}

	public static Map<Integer, Runnable> getAdminOperations() {
		return adminOperations;
	}

	public static Map<Integer, Runnable> getQuizAccess() {
		return quizAccess;
	}

	public static Map<Integer, Runnable> getQuestionAccess() {
		return questionAccess;
	}

	public static Map<Integer, Runnable> getUserLogin() {
		return userLogin;
	}

	public static Map<Integer, Runnable> getUserOperations() {
		return userOperations;
	}

	public static Map<Integer, Consumer<Integer>> getUpdateQuestion() {
		return updateQuestion;
	}
	public static Map<Integer, Consumer<Integer>> getUpdateQuiz() {
		return updateQuiz;
	}
}
