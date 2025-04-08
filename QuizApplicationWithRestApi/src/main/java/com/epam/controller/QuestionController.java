package com.epam.controller;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.service.QuestionService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	private String listOfQuestions = "listOfQuestions";
	private String createQuestion = "createquestion";
	private String questionPage = "questionpage";

	@RequestMapping("/createquestion")
	public String createQuestionPage() {
		return createQuestion;
	}

	@RequestMapping("/questionpage")
	public ModelAndView questionPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(questionPage);
		if (!questionService.displayQuestions().isEmpty()) {
			mv.addObject(listOfQuestions, questionService.displayQuestions());
		} else {
			mv.addObject("emptyquestionlibrary", "Empty Question Library..");
		}
		return mv;

	}

	@PostMapping("/addquestion")
	public ModelAndView createQuestion(QuestionDto question) throws QuestionException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(createQuestion);
		if (IntStream.range(1, question.getOptions().size() + 1).boxed().toList().containsAll(question.getAnswers())) {
			questionService.addQuestion(question);
			modelAndView.addObject(createQuestion, "question created succesfull..");
		} else {
			modelAndView.addObject("cannotcreatequestion", "cannot create question with invalid answers");
		}
		return modelAndView;
	}

	@GetMapping("/deletequestion")
	public ModelAndView deleteQuestion(int qNo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(questionPage);
		try {
			questionService.removeQuestion(qNo);
			mv.addObject(listOfQuestions, questionService.displayQuestions());
			mv.addObject("deletequestionmessage", "Question Deleted Succesfully...");

		} catch (Exception e) {
			mv.addObject(listOfQuestions, questionService.displayQuestions());
			mv.addObject("cannotdelete", "Cannot delete this question...");
		}
		return mv;
	}

	@RequestMapping("/updatequestion")
	public ModelAndView updateQuestionPage(Integer qNo) throws QuestionException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatequestion");
		QuestionDto question = questionService.getQuestion(qNo);
		mv.addObject("question", questionService.getQuestion(qNo));
		mv.addObject("options", question.getOptions().stream().collect(Collectors.joining(",")));
		mv.addObject("answers", question.getAnswers().stream().map(Object::toString).collect(Collectors.joining(",")));
		return mv;
	}

	@RequestMapping("/updatequestionwithdetails")
	public ModelAndView updatingQuestion(QuestionDto question) throws QuestionException {
		ModelAndView mv = new ModelAndView();
		questionService.updateQuestion(question);
		mv.setViewName(questionPage);
		mv.addObject(listOfQuestions, questionService.displayQuestions());
		mv.addObject("updatequestion", question.getId() + " Question updated succesfully...");
		return mv;
	}
}
