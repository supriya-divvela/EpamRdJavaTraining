package com.epam.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.service.QuestionService;

@Component
public class QuestionServiceUIImplementation implements QuestionServiceUI {
	private Logger logger = LogManager.getLogger("QuestionFunctionalitiesImplementation.class");
	@Autowired
	private QuestionService questionService;
	private static String enterQuestionNumber = "Enter question number:";

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
				getUpdateQuestion().get(choice).accept(qNo);
				logger.info(
						"Enter What do you want to update:\n1)Title\n2)Options\n3)Difficulty\n4)Tagging topic\n5)Answer\n6)Marks\n7)Exit\n");
				choice = Input.readInt();
			} while (choice != 7);
		} catch (InputMismatchException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	@Override
	public Map<Integer, Runnable> getQuestionAccess() {
		Map<Integer, Runnable> questionAccess = new HashMap<>();
		questionAccess.put(1, () -> {
			logger.info(enterQuestionNumber);
			int qNo = Input.readInt();
			if (!questionService.findQuestion(qNo)) {
				createQuestion(qNo);
				logger.info("Question Added Successfully..");
			} else {
				logger.info("Duplicate Question Number Exception...");
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
					updateQuestion(qNo);
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
		return questionAccess;
	}

	@Override
	public Map<Integer, Consumer<Integer>> getUpdateQuestion() {
		Map<Integer, Consumer<Integer>> updateQuestion = new HashMap<>();
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
					logger.info("Enter option {} :", i);
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
					logger.info("Enter number of answers u want to add in the range(1,{}) :", numberOfOptions);
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
		return updateQuestion;
	}
}
