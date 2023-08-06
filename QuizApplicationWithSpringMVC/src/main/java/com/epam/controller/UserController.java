package com.epam.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.UserDto;
import com.epam.model.Question;
import com.epam.service.LoginService;
import com.epam.service.QuizService;
import com.epam.service.RegisterService;

@Controller
public class UserController {
	@Autowired
	private QuizService quizService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private RegisterService registerService;
	private String userLogin = "userlogin";
	private String listOfQuizs = "listOfQuizs";

	@RequestMapping("/user")
	public ModelAndView displayQuizes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user");
		mv.addObject(listOfQuizs, quizService.viewQuizs());
		return mv;
	}

	@GetMapping("/attemptquiz")
	public ModelAndView attemptQuiz(int quizNo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("attemptquiz");
		mv.addObject("quizNo", quizNo);
		mv.addObject("questions", quizService.getQuiz(quizNo).getListOfQuestions());
		return mv;
	}

	@GetMapping("/marks")
	public ModelAndView marks(Integer quizNo, @RequestParam List<HashSet<Integer>> answers) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("marks");
		int marksobtained = 0;
		List<Question> questions = quizService.getQuiz(quizNo).getListOfQuestions();
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).equals(questions.get(i).getAnswers())) {
				marksobtained = marksobtained + questions.get(i).getMarks();
			}
		}
		mv.addObject("quizNo", quizNo);
		mv.addObject("marksobtained", marksobtained);
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
	public ModelAndView addUser(String username, String password, String confirmpassword) {
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

	@PostMapping("/verifyuser")
	public ModelAndView verifyUser(UserDto user) {
		ModelAndView mv = new ModelAndView();
		if (loginService.getAuthentication(user)) {
			mv.setViewName("user");
			mv.addObject("listOfQuizs", quizService.viewQuizs());
			mv.addObject("login", "User Logged in succesfully.........");
		} else {
			mv.setViewName(userLogin);
			mv.addObject("invaliduser", "Invalid User...");
		}
		return mv;
	}
}
