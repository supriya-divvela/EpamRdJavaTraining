package com.epam.ui;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.exception.DuplicateUserException;
import com.epam.model.User;
import com.epam.service.LoginService;
import com.epam.service.QuizService;
import com.epam.service.RegisterService;

@Component
public class UserUI {
	private Logger logger = LogManager.getLogger("UserUI.class");
	@Autowired
	private LoginService loginService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private QuizService quizService;
	@Autowired
	private UserServiceUI userServiceUI;
	@Autowired
	private AdminServiceUI adminServiceUI;
	private String enterProperNumber = "Enter proper number..";

	public void userOperations() {
		String variousOptions = "Enter number to proceed\n1)Press 1 for login\n2)Press 2 for register\n3)Press 3 for exit";
		try {
			logger.info(variousOptions);
			int logintype = Input.readInt();
			do {
				if (logintype == 1) {
					userLogin(enterUserDetails("user"));
				} else if (logintype == 2) {
					userRegister(enterUserDetails("user"));
				} else if (logintype == 3) {
					break;
				} else {
					logger.info("Enter Proper number..");
				}
				logger.info(variousOptions);
				logintype = Input.readInt();
			} while (logintype != 3);
		} catch (Exception e) {
			logger.info("Please Enter numbers only..");
		}
	}

	public User enterUserDetails(String usertype) {
		String username;
		String password;
		logger.info("Enter UserName :");
		username = Input.read();
		logger.info("Enter Password :");
		password = Input.read();
		return new User(username, password, usertype);
	}

	public void userLogin(User user) {
		String variousOptions = "Enter number to proceed...\n1)Question Library\n2)Quiz Library\n3)Exit";
		if (user.getUsertype().equalsIgnoreCase("admin")) {
			if (loginService.getAuthentication(user)) {
				logger.info("Logged in succesfully...");
				logger.info(variousOptions);
				adminAccessAfterLogin();
			} else {
				logger.info("Incorrect Password or No user exits\n");
				adminOperations();
			}
		} else if (user.getUsertype().equalsIgnoreCase("user")) {
			userAccessAfterLogin(user);
		} else {
			logger.info("Incorrect Password or No user exits\n");
			userOperations();
		}
	}

	public void userAccessAfterLogin(User user) {
		String options="Enter number to proceed...\n1)Want to attempt a quiz\n2)Display all avialable Quizes\n3)Exit";
		String allAvailableQuizes = quizService.displayAvaliableQuizes().stream().map(Object::toString).collect(Collectors.joining("\n"));
		if (loginService.getAuthentication(user)) {
			logger.info("Logged in succesfully...");
			logger.info(options);
			int choice = Input.readInt();
			do {
				if (choice == 1) {
					userServiceUI.attemptQuiz();
				} else if (choice == 2) {
					logger.info("All Available quizes are:");
					logger.info(allAvailableQuizes);
				} else if (choice == 3) {
					break;
				} else {
					logger.info(enterProperNumber);
				}
				logger.info(options);
				choice = Input.readInt();
			} while (choice != 3);
		}

	}

	public void adminAccessAfterLogin() {
		String variousOptions = "Enter number to proceed...\n1)Question Library\n2)Quiz Library\n3)Exit";
		int choice = Input.readInt();
		do {
			if (choice == 1) {
				adminServiceUI.questionsAccess();
			} else if (choice == 2) {
				adminServiceUI.quizAccess();
			} else if (choice == 3) {
				break;
			} else {
				logger.info(enterProperNumber);
			}
			logger.info(variousOptions);
			choice = Input.readInt();
		} while (choice != 3);
	}

	public void adminOperations() {
		String variousOptions = "Enter number to proceed\n1)Press 1 for login\n2)Press 2 for exit";
		logger.info(variousOptions);
		int choice = Input.readInt();
		do {
			if (choice == 1) {
				userLogin(enterUserDetails("admin"));
			} else if (choice == 2) {
				break;
			} else {
				logger.info("Enter proper number..");
			}
			logger.info(variousOptions);
			choice = Input.readInt();
		} while (choice != 2);
	}

	public void userRegister(User user) {
		if (loginService.getAuthentication(user)) {
			logger.info("Already user exits with this username...");
		} else {
			logger.info("Confirm Your Password :");
			String confirmPassword = Input.read();
			if (user.getPassword().equals(confirmPassword)) {
				try {
					registerService.addUser(user);
				} catch (DuplicateUserException e) {
					logger.info("User Already registered...");
				}
				logger.info("Registered Successfully...\n");
			} else {
				logger.info("password and confirm password are not same\n");
			}
		}
	}
}
