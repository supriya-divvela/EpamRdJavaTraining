package com.epam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.exception.QuizException;
import com.epam.service.LoginService;
import com.epam.service.QuizService;
import com.epam.service.RegisterService;
import com.epam.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
	@Autowired
	private QuizService quizService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@Autowired
	private RegisterService registerService;
	private String userLogin = "userlogin";
	private String listOfQuizs = "listOfQuizs";

	@RequestMapping("/user")
	public ModelAndView displayQuizes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user");
		mv.addObject(listOfQuizs, quizService.displayAvaliableQuizes());
		return mv;
	}

	@GetMapping("/attemptquiz")
	public ModelAndView attemptQuiz(int quizNo) throws QuizException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("attemptquiz");
		mv.addObject("quizNo", quizNo);
		mv.addObject("questions", quizService.getQuiz(quizNo).getListOfQuestions());
		return mv;
	}

	@RequestMapping("/marks")
	public ModelAndView marks(@RequestParam Integer quizNo, HttpServletRequest request) throws QuizException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("marks");
		List<List<Integer>> answers = new ArrayList<>(request.getParameterMap().entrySet().stream()
				.map(x -> Stream.of(x.getValue()).map(Integer::parseInt).toList()).toList());
		answers.remove(0);
		int marksobtained = userService.calculateMarks(answers, quizService.getQuiz(quizNo));
		int totalMarks = userService.calculateTotalMarks(quizService.getQuiz(quizNo));
		mv.addObject("quizNo", quizNo);
		mv.addObject("marksobtained", marksobtained);
		mv.addObject("totalmarks", totalMarks);
		return mv;
	}

	@RequestMapping("/userlogin")
	public String userLoginPage() {
		return userLogin;
	}

	@RequestMapping("/userregister")
	public String userRegisterPage() {
		return "userregister";
	}

	@RequestMapping("/register")
	public ModelAndView addUser(String username, String password, String confirmpassword)
			throws UserException {
		ModelAndView mv = new ModelAndView();
		if (loginService.getAuthentication(new UserDto(username, password, "user"))) {
			mv.setViewName(userLogin);
			mv.addObject("alreadyregistered", "User already registered...please try login");
		} else {
			if (password.equals(confirmpassword)) {
				registerService.addUser(new UserDto(username, password, "user"));
				mv.setViewName(userLogin);
				mv.addObject("userregisterd", "user registered succesfully...");
			} else {
				mv.setViewName("userregister");
				mv.addObject("passwordincorrect", "password and confirm password did not match...");
			}
		}
		return mv;
	}

	@GetMapping("/verifyuser")
	public ModelAndView verifyUser(UserDto user) {
		ModelAndView mv = new ModelAndView();
		if (loginService.getAuthentication(user)) {
			mv.setViewName("user");
			mv.addObject("listOfQuizs", quizService.displayAvaliableQuizes());
			mv.addObject("login", "User Logged in succesfully.........");
		} else {
			mv.setViewName(userLogin);
			mv.addObject("invaliduser", "Invalid User...");
		}
		return mv;
	}
}
