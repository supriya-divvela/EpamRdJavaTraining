package com.epam.ui;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.model.User;
import com.epam.service.LoginService;
import com.epam.service.QuizService;
import com.epam.service.QuizServiceImplementation;
import com.epam.service.RegisterService;

public class UserUI {
	private Logger logger = (Logger) LogManager.getLogger("UserFunctionalities.class");
	private Scanner scanner = new Scanner(System.in);
	private LoginService loginService = new LoginService();
	private RegisterService registerService = new RegisterService();
	private QuizService quizService = new QuizServiceImplementation();

	public void userOperations() {
		try {
			logger.info("Enter number to proceed\n" + "1)Press 1 for login\n"
					+ "2)Press 2 for register\n3)Press 3 for exit");
			int logintype = Integer.parseInt(scanner.nextLine());
			do {
				if (logintype == 3) {
					break;
				}
				if (UiOperations.getUserOperations().get(logintype) == null) {
					logger.info("Enter Proper number..");
				} else {
					UiOperations.getUserOperations().get(logintype).run();
				}
				logger.info("Enter number to proceed\n" + "1)Press 1 for login\n"
						+ "2)Press 2 for register\n3)Press 3 for exit");
				logintype = Integer.parseInt(scanner.nextLine());
			} while (logintype != 3);
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	public User enterUserDetails() {
		String username;
		String password;
		logger.info("Enter UserName :");
		username = scanner.nextLine();
		logger.info("Enter Password :");
		password = scanner.nextLine();
		logger.info("Enter Usertype :");
		String usertype = scanner.nextLine();
		User user = new User(username, password, usertype);
		return user;
	}

	public void userLogin() {
		User user = enterUserDetails();
		try {
			if (user.getUsertype().equalsIgnoreCase("admin")) {
				if (loginService.getAuthentication(user)) {
					logger.info("Logged in succesfully...");
					logger.info("Enter number to proceed...\n" + "1)Question Library\n" + "2)Quiz Library\n"
							+ "3)Create new admin\n4)Exit");
					int choice = Integer.parseInt(scanner.nextLine());
					do {
						if (choice == 4) {
							break;
						}
						if (UiOperations.getAdminLogin().get(choice) == null) {
							logger.info("Enter proper number1..");
						} else {
							UiOperations.getAdminLogin().get(choice).run();
						}
						logger.info("Enter number to proceed...\n" + "1)Question Library\n" + "2)Quiz Library\n"
								+ "3)Create new admin\n4)Exit");
						choice = Integer.parseInt(scanner.nextLine());
					} while (choice != 4);
				} else {
					logger.info("Incorrect Password or No user exits\n");
					adminOperations();
				}
			} else if (user.getUsertype().equalsIgnoreCase("user")) {
				if (loginService.getAuthentication(user)) {
					logger.info("Logged in succesfully...");
					logger.info("Enter number to proceed...\n" + "1)Want to attempt a quiz\n"
							+ "2)Display all avialable Quizes\n" + "3)Exit");
					int choice = Integer.parseInt(scanner.nextLine());
					do {
						if (choice == 3) {
							break;
						}
						if (UiOperations.getUserLogin().get(choice) == null) {
							logger.info("Enter proper number..");
						} else {
							UiOperations.getUserLogin().get(choice).run();
						}
						logger.info("Enter number to proceed...\n" + "1)Want to attempt a quiz\n"
								+ "2)Display all avialable Quizes\n" + "3)Exit");
						choice = Integer.parseInt(scanner.nextLine());
					} while (choice != 3);
				} else {
					logger.info("Incorrect Password or No user exits\n");
					userOperations();
				}
			}
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	public void adminOperations() {
		try {
			logger.info("Enter number to proceed\n" + "1)Press 1 for login\n" + "2)Press 2 for exit");
			int choice = Integer.parseInt(scanner.nextLine());
			do {
				if (choice == 2) {
					break;
				}
				if (UiOperations.getAdminOperations().get(choice) == null) {
					logger.info("Enter proper number..");
				} else {
					UiOperations.getAdminOperations().get(choice).run();
				}
				logger.info("Enter number to proceed\n" + "1)Press 1 for login\n" + "2)Press 2 for exit");
				choice = Integer.parseInt(scanner.nextLine());
			} while (choice != 2);
		} catch (NumberFormatException e) {
			logger.info("Please Enter numbers only..");
		}
	}

	public void userRegister() {
		User user = enterUserDetails();
		if (loginService.getAuthentication(user)) {
			logger.info("Already user exits with this username...");
		} else {
			logger.info("Confirm Your Password :");
			String confirmPassword = scanner.nextLine();
			if (user.getPassword().equals(confirmPassword)) {
				registerService.addUser(user);
				logger.info("Registered Successfully...\n");
			} else {
				logger.info("password and confirm password are not same\n");
			}
		}
	}

	public void attemptQuiz() {
		logger.info(quizService.displayAvaliableQuizes());
		logger.info("Enter Which quiz u want to attempt :");
		try {
			int quizNo = Integer.parseInt(scanner.nextLine());
			int totalmarks = 0;
			if (quizService.findQuiz(quizNo)) {
				Quiz quiz = quizService.getQuiz(quizNo);
				Map<Question, Integer> questions = quizService.getQuiz(quizNo).getListOfQuestions();
				for (Map.Entry<Question, Integer> question : questions.entrySet()) {
					logger.info(question);
					int numberOfOptions = question.getKey().getOptions().size();
					logger.info("Enter how many answers you want to add :");
					int numberOfAnswers = Integer.parseInt(scanner.nextLine());
					TreeSet<Integer> answers = new TreeSet<Integer>();
					do {
						if (numberOfAnswers <= numberOfOptions && numberOfAnswers > 0) {
							for (int i = 1; i <= numberOfAnswers; i++) {
								logger.info("Enter " + i + " answer :");
								int answer = Integer.parseInt(scanner.nextLine());
								do {
									if (answer <= numberOfOptions) {
										answers.add(answer);
										break;
									}
									logger.info("Enter " + i + " answer :");
									answer = Integer.parseInt(scanner.nextLine());
								} while (answer > numberOfOptions || answer < 0);
							}
							break;
						}
						logger.info("Enter how many answers you want to add :");
						numberOfAnswers = Integer.parseInt(scanner.nextLine());
					} while (numberOfAnswers > numberOfOptions || numberOfAnswers < 0);
					if (answers.equals(question.getKey().getAnswers())) {
						totalmarks += question.getValue();
					}
				}
				logger.info("Total marks obtained is :" + totalmarks + "/" + quiz.getTotalMarks());
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
	}
}
