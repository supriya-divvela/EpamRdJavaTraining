package com.epam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.QuizDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Quiz;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;

@Controller
public class QuizController {
	@Autowired
	private QuizService quizService;
	@Autowired
	private QuestionService questionService;
	private String quizpage = "quizpage";
	private String listOfQuizs = "listOfQuizs";
	private String createQuiz = "createquiz";
	private String listOfQuestions = "listOfQuestions";

	@RequestMapping("/quizpage")
	public ModelAndView quizPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(quizpage);
		mv.addObject(listOfQuizs, quizService.displayAvaliableQuizes());
		if (quizService.displayAvaliableQuizes().isEmpty()) {
			mv.addObject("emptyquizlibrary", "Empty quiz library...");
		}
		return mv;
	}

	@RequestMapping("/createquiz")
	public ModelAndView createQuizPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(createQuiz);
		mv.addObject(listOfQuestions, questionService.displayQuestions());
		return mv;
	}

	@PostMapping("/addquiz")
	public ModelAndView addQuiz(String title, @RequestParam List<Integer> listOfQuestions)
			throws QuestionNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(createQuiz);
		modelAndView.addObject("listOfQuestions", questionService.displayQuestions());
		QuizDto quiz = quizService.createQuiz(title, listOfQuestions);
		quizService.addQuiz(quiz);
		modelAndView.addObject(createQuiz, "quiz created succesfull..");
		return modelAndView;
	}

	@RequestMapping("/deletequiz")
	public ModelAndView deleteQuiz(int quizNo) throws QuizNotFoundException {
		ModelAndView mv = new ModelAndView();
		quizService.removeQuiz(quizNo);
		mv.setViewName(quizpage);
		mv.addObject(listOfQuizs, quizService.displayAvaliableQuizes());
		mv.addObject("deletequizmessage", "Quiz Deleted Succesfully...");
		return mv;
	}

	@RequestMapping("/updatequiz")
	public ModelAndView updateQuizPage(int quizNo) throws QuizNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatequiz");
		Quiz quiz = quizService.getQuiz(quizNo);
		mv.addObject("quiz", quizService.getQuiz(quizNo));
		mv.addObject("questionnumbers", quiz.getListOfQuestions().stream().map(question -> question.getId() + "")
				.collect(Collectors.joining(",")));
		mv.addObject(listOfQuestions, questionService.displayQuestions());
		return mv;
	}

	@PostMapping("/updatequizwithdetails")
	public ModelAndView updatingQuiz(int quizNo, String title, @RequestParam List<Integer> listOfQuestions)
			throws QuestionNotFoundException, QuizNotFoundException {
		QuizDto quizDto = quizService.createQuiz(title, listOfQuestions);
		ModelAndView mv = new ModelAndView();
		quizService.updateQuiz(quizNo, quizDto);
		mv.setViewName(quizpage);
		mv.addObject(listOfQuizs, quizService.displayAvaliableQuizes());
		mv.addObject("updatequiz", quizDto.getId() + " Question updated succesfully...");
		return mv;
	}
}
