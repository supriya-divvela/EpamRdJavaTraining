package com.epam.ui;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.service.QuizService;
import com.epam.service.QuizServiceImplementation;

public class UserServiceUIImplementation implements UserServiceUI {
	private Logger logger = (Logger) LogManager.getLogger("UserServiceUIImplementation.class");
	private QuizService quizService = new QuizServiceImplementation();

	@Override
	public void attemptQuiz() {
		String allAvailableQuizes = quizService.displayAvaliableQuizes();
		logger.info(allAvailableQuizes);
		logger.info("Enter Which quiz u want to attempt :");
		int quizNo = Input.readInt();
		int totalMarks = 0;
		int marksObtained = 0;
		try {
			if (quizService.findQuiz(quizNo)) {
				List<Question> questions = quizService.getQuiz(quizNo).getListOfQuestions();
				for (Question question : questions) {
					logger.info(question);
					Set<Integer> answers = attemptQuestions(question);
					if (answers.equals(question.getAnswers())) {
						marksObtained += question.getMarks();
					}
					totalMarks += question.getMarks();
				}
				logger.info("Total marks obtained is :{}/{}", marksObtained, totalMarks);
			} else {
				throw new QuizNotFoundException();
			}
		} catch (QuizNotFoundException e) {
			logger.info(e);
		}
	}

	public Set<Integer> attemptQuestions(Question question) {
		int numberOfOptions = question.getOptions().size();
		logger.info("Enter how many answers you want to add :");
		int numberOfAnswers = Input.readInt();
		Set<Integer> answers = new TreeSet<>();
		do {
			if (numberOfAnswers <= numberOfOptions && numberOfAnswers > 0) {
				answers = attemptAnswers(numberOfAnswers, numberOfOptions);
				break;
			}
			logger.info("Enter how many answers you want to add :");
			numberOfAnswers = Input.readInt();
		} while (numberOfAnswers > numberOfOptions || numberOfAnswers < 0);
		return answers;
	}

	public Set<Integer> attemptAnswers(int numberOfAnswers, int numberOfOptions) {
		Set<Integer> answers = new TreeSet<>();
		for (int i = 1; i <= numberOfAnswers; i++) {
			logger.info("Enter {} answer :", i);
			int answer = Input.readInt();
			do {
				if (answer <= numberOfOptions) {
					answers.add(answer);
					break;
				}
				logger.info("Enter {} answer :", i);
				answer = Input.readInt();
			} while (answer > numberOfOptions || answer < 0);
		}
		return answers;
	}
}
