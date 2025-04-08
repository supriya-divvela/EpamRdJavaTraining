package com.epam.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.QuestionDto;
import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.service.QuestionService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	private String listOfQuestions="listOfQuestions";
	private String createQuestion="createquestion";
	private String questionPage="questionpage";
	@RequestMapping("/createquestion")
	public String createQuestionPage() {
		return createQuestion;
	}

	@RequestMapping("/questionpage")
	public ModelAndView questionPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(questionPage);
		try {
			mv.addObject(listOfQuestions, questionService.viewQuestions());
		} catch (EmptyQuestionLibraryException e) {
			mv.addObject("emptyquestionlibrary", "Empty Question Library..");
		}
		return mv;

	}

	@PostMapping("/addquestion")
	public ModelAndView createQuestion(QuestionDto question) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			questionService.addQuestion(question);
			modelAndView.setViewName(createQuestion);
			modelAndView.addObject(createQuestion, "question created succesfull..");

		} catch (DuplicateQuestionNumberException e) {
			modelAndView.setViewName(createQuestion);
			modelAndView.addObject("duplicatequestion", "duplicate question number found");
		}
		return modelAndView;
	}

	@GetMapping("/deletequestion")
	public ModelAndView deleteQuestion(int qNo) throws EmptyQuestionLibraryException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(questionPage);
		try {
			questionService.removeQuestion(questionService.getQuestion(qNo));
			mv.addObject(listOfQuestions, questionService.viewQuestions());
			mv.addObject("deletequestionmessage", "Question Deleted Succesfully...");
		} catch (Exception e) {
			mv.addObject("emptyquestionlibrary", "Empty question library...");
		}
		return mv;
	}

	@RequestMapping("/updatequestion")
	public ModelAndView updateQuestionPage(Integer qNo) throws QuestionNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatequestion");
		Question question = questionService.getQuestion(qNo);
		mv.addObject("question", questionService.getQuestion(qNo));
		mv.addObject("options", question.getOptions().stream().collect(Collectors.joining(",")));
		mv.addObject("answers", question.getAnswers().stream().map(Object::toString).collect(Collectors.joining(",")));
		return mv;
	}

	@RequestMapping("/updatequestionwithdetails")
	public ModelAndView updatingQuiz(QuestionDto question)
			throws EmptyQuestionLibraryException, QuestionNotFoundException {
		ModelAndView mv = new ModelAndView();
		questionService.updateQuestion(question.getQNo(), question);
		mv.setViewName(questionPage);
		mv.addObject(listOfQuestions, questionService.viewQuestions());
		mv.addObject("updatequestion", question.getQNo() + " Question updated succesfully...");
		return mv;
	}
}
