package com.epam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.exception.DuplicateQuizNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Quiz;
import com.epam.service.QuizService;

@Controller
public class QuizController {
	@Autowired
	private QuizService quizService;
	private String quizpage="quizpage";
	private String listOfQuizs="listOfQuizs";
	private String createQuiz="createQuiz";
	@RequestMapping("/quizpage")
	public ModelAndView quizPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(quizpage);
		mv.addObject(listOfQuizs, quizService.viewQuizs());
		if (quizService.viewQuizs().isEmpty()) {
			mv.addObject("emptyquizlibrary", "Empty quiz library...");
		}
		return mv;
	}

	@RequestMapping("/createquiz")
	public String createQuizPage() {
		return createQuiz;
	}

	@PostMapping("/addquiz")
	public ModelAndView addQuiz(int quizNo, String title, @RequestParam List<Integer> listOfQuestions)
			throws EmptyQuestionLibraryException {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Quiz quiz = quizService.createQuiz(quizNo, title, listOfQuestions);
			quizService.addQuiz(quiz);
			modelAndView.setViewName(createQuiz);
			modelAndView.addObject(createQuiz, "quiz created succesfull..");
		} catch (DuplicateQuizNumberException | QuestionNotFoundException e) {
			modelAndView.setViewName(createQuiz);
			modelAndView.addObject("duplicatequiz", "duplicate quiz number found");
		}
		return modelAndView;
	}

	@RequestMapping("/deletequiz")
	public ModelAndView deleteQuiz(int quizNo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(quizpage);
		quizService.removeQuiz(quizService.getQuiz(quizNo));
		mv.addObject(listOfQuizs, quizService.viewQuizs());
		mv.addObject("deletequizmessage", "Quiz Deleted Succesfully...");
		return mv;
	}

	@RequestMapping("/updatequiz")
	public ModelAndView updateQuizPage(int quizNo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatequiz");
		Quiz quiz = quizService.getQuiz(quizNo);
		mv.addObject("quiz", quizService.getQuiz(quizNo));
		mv.addObject("questionnumbers", quiz.getListOfQuestions().stream().map(question -> question.getQNo() + "")
				.collect(Collectors.joining(",")));
		return mv;
	}

	@PostMapping("/updatequizwithdetails")
	public ModelAndView updatingQuiz(int quizNo, String title, @RequestParam List<Integer> listOfQuestions)
			throws EmptyQuestionLibraryException, QuestionNotFoundException, DuplicateQuizNumberException {
		Quiz quiz = quizService.createQuiz(quizNo, title, listOfQuestions);
		ModelAndView mv = new ModelAndView();
		quizService.updateQuiz(quizNo, quiz);
		mv.setViewName(quizpage);
		mv.addObject(listOfQuizs, quizService.viewQuizs());
		mv.addObject("updatequiz", quiz.getQuizNo() + " Question updated succesfully...");
		return mv;
	}
}
