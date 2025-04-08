package com.epam.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private static Map<Integer, Runnable> quizAccess = new HashMap<>();
	private static Map<Integer, Runnable> questionAccess = new HashMap<>();
	private static Map<Integer, Consumer<Integer>> updateQuestion = new HashMap<>();
	private static Map<Integer, Consumer<Integer>> updateQuiz = new HashMap<>();
	private static Logger logger = (Logger) LogManager.getLogger("UiOperations.class");
	private static QuestionService questionService = new QuestionServiceImplementation();
	private static QuizService quizService = new QuizServiceImplementation();
	private static QuizServiceUI quizServiceUI = new QuizServiceUIImplementation();
	private static QuestionServiceUI questionServiceUI = new QuestionServiceUIImplementation();

	private UiOperations() {

	}

	static {
		String enterQuizNumber = "Enter quiz number:";
		String enterQuestionNumber = "Enter question number:";
		// Quiz Access Options
		quizAccess.put(1, () -> {
			logger.info(enterQuizNumber);
			int quizNo = Input.readInt();
			try {
				if (!quizService.findQuiz(quizNo)) {
					quizServiceUI.createQuiz(quizNo);
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
					quizServiceUI.updateQuiz(quizNo);
					logger.info("Updated Succesfully..");
				} else {
					throw new QuizNotFoundException();
				}
			} catch (QuizNotFoundException | EmptyQuestionLibraryException e) {
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
		// Question Access Options
		questionAccess.put(1, () -> {
			logger.info(enterQuestionNumber);
			int qNo = Input.readInt();
			try {
				if (!questionService.findQuestion(qNo)) {
					questionServiceUI.createQuestion(qNo);
					logger.info("Question Added Successfully..");
				} else {
					throw new DuplicateQuestionNumberException();
				}
			} catch (DuplicateQuestionNumberException e) {
				logger.info(e);
			}
		});
		questionAccess.put(2, () -> {
			try {
				if (!questionService.isEmptyQuestionLibrary()) {
					String allAvailableQuestions = questionService.displayQuestions();
					logger.info(allAvailableQuestions);
				} else {
					throw new EmptyQuestionLibraryException();
				}
				logger.info(enterQuestionNumber);
				int qNo = Input.readInt();
				if (questionService.findQuestion(qNo)) {
					questionService.removeQuestion(questionService.getQuestion(qNo));
					logger.info("Removed succesfully");
				} else {
					throw new QuestionNotFoundException();
				}
			} catch (Exception e) {
				logger.info(e);
			}
		});
		questionAccess.put(3, () -> {
			try {
				if (!questionService.isEmptyQuestionLibrary()) {
					String allAvailableQuestions = questionService.displayQuestions();
					logger.info(allAvailableQuestions);
				} else {
					throw new EmptyQuestionLibraryException();
				}
				logger.info(enterQuestionNumber);
				int qNo = Input.readInt();
				if (questionService.findQuestion(qNo)) {
					questionServiceUI.updateQuestion(qNo);
					logger.info("Updated Succesfully");
				} else {
					throw new QuestionNotFoundException();
				}
			} catch (QuestionNotFoundException | EmptyQuestionLibraryException e) {
				logger.info(e);
			}
		});
		questionAccess.put(4, () -> {
			try {
				if (!questionService.isEmptyQuestionLibrary()) {
					logger.info(questionService.displayQuestions());
				} else {
					throw new EmptyQuestionLibraryException();
				}
			} catch (EmptyQuestionLibraryException e) {
				logger.info(e);
			}
		});

		// update Question
		updateQuestion.put(1, qNo -> {
			logger.info("Enter title of the question : ");
			String title = Input.read();
			try {
				questionService.updateTitle(qNo, title);
			} catch (QuestionNotFoundException e) {
				logger.info(e);
			}
		});
		updateQuestion.put(2, qNo -> {
			logger.info("Enter Options size :");
			int numberOfOptions = Input.readInt();
			try {
				List<String> options = new ArrayList<>();
				for (int i = 1; i <= numberOfOptions; i++) {
					logger.info("Enter option " + i + " :");
					options.add(Input.read());
				}
				questionService.updateOptions(qNo, options);
			} catch (QuestionNotFoundException e) {
				logger.info(e);
			}
		});
		updateQuestion.put(3, qNo -> {
			logger.info("Enter difficulty :");
			String difficulty = Input.read();
			try {
				questionService.updateDifficulty(qNo, difficulty);
			} catch (QuestionNotFoundException e) {
				logger.info(e);
			}
		});
		updateQuestion.put(4, qNo -> {
			logger.info("Enter tagging topic");
			String taggingTopic = Input.read();
			try {
				questionService.updateTaggingTopic(qNo, taggingTopic);
			} catch (QuestionNotFoundException e) {
				logger.info(e);
			}
		});
		updateQuestion.put(5, qNo -> {
			Question question = null;
			try {
				question = questionService.getQuestion(qNo);
				int numberOfOptions = question.getOptions().size();
				Set<Integer> newAnswers = new TreeSet<>();
				int numberOfAnswersUpdate = 0;
				do {
					logger.info("Enter number of answers u want to add in the range(1," + numberOfOptions + ") :");
					numberOfAnswersUpdate = Input.readInt();
					if (numberOfAnswersUpdate <= numberOfOptions && numberOfAnswersUpdate >= 1) {
						for (int i = 1; i <= numberOfAnswersUpdate; i++) {
							int optionUpdate = 0;
							do {
								logger.info("Enter answer to update :");
								 optionUpdate = Input.readInt();
								if (optionUpdate <= numberOfOptions && numberOfOptions >= 1) {
									newAnswers.add(optionUpdate);
									break;
								}
							} while (optionUpdate > numberOfOptions || optionUpdate < 1);
						}
						questionService.updateAnswers(qNo, newAnswers);
					}
				} while (numberOfAnswersUpdate > numberOfOptions || numberOfAnswersUpdate < 1);
			} catch (QuestionNotFoundException e) {
				logger.info(e);
			}
		});
		updateQuestion.put(6, qNo -> {
			logger.info("Enter marks for question :");
			int marks = Input.readInt();
			try {
				questionService.updateMarks(qNo, marks);
			} catch (QuestionNotFoundException e) {
				logger.info(e);
			}
		});
		// Update Quiz
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
	}

	public static Map<Integer, Runnable> getQuizAccess() {
		return quizAccess;
	}

	public static Map<Integer, Runnable> getQuestionAccess() {
		return questionAccess;
	}

	public static Map<Integer, Consumer<Integer>> getUpdateQuestion() {
		return updateQuestion;
	}

	public static Map<Integer, Consumer<Integer>> getUpdateQuiz() {
		return updateQuiz;
	}
}
