package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.QuestionController;
import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.service.QuestionService;
@WebMvcTest(QuestionController.class)
class TestQuestionController {
	@MockBean
	private QuestionService questionService;
	@Autowired
	private MockMvc mockMvc;
	@Test
	void testCreateQuestionPage() throws Exception {
		mockMvc.perform(get("/createquestion")).andExpect(status().isOk()).andExpect(view().name("createquestion"));
	}

	@Test
	void testQuestionPage() throws Exception {
		List<QuestionDto> questions = new ArrayList<>();
		questions.add(new QuestionDto());
		when(questionService.displayQuestions()).thenReturn(questions);
		mockMvc.perform(get("/questionpage")).andExpect(status().isOk()).andExpect(view().name("questionpage"))
				.andExpect(model().attribute("listOfQuestions", questions));

	}

	@Test
	void testQuestionPageWithNoQuestions() throws Exception {
		List<QuestionDto> questions = new ArrayList<>();
		when(questionService.displayQuestions()).thenReturn(questions);
		mockMvc.perform(get("/questionpage")).andExpect(status().isOk()).andExpect(view().name("questionpage"))
				.andExpect(model().attribute("emptyquestionlibrary", "Empty Question Library.."));

	}

	@Test
	void testCreateQuestion() throws Exception {
		QuestionDto questionDto = new QuestionDto();
		when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
		mockMvc.perform(post("/addquestion").param("qNo", "2").param("marks", "2")
				.param("title", questionDto.getTitle()).param("options", "1,2,3,4").param("answers", "2")
				.param("difficulty", "hard").param("taggingTopic", "java").param("marks", "5"))
				.andExpect(status().isOk()).andExpect(view().name("createquestion"))
				.andExpect(model().attribute("createquestion", "question created succesfull.."));
	}

	@Test
	void testCreateQuestionWithException() throws Exception {
		mockMvc.perform(post("/addquestion").param("qNo", "2").param("marks", "2").param("title", "hii")
				.param("options", "1,2,3,4").param("answers", "5,6,7").param("difficulty", "hard")
				.param("taggingTopic", "java")).andExpect(status().isOk()).andExpect(view().name("createquestion"))
				.andExpect(model().attribute("cannotcreatequestion", "cannot create question with invalid answers"));
	}

	@Test
	void testDeleteQuestion() throws Exception {
		doNothing().when(questionService).removeQuestion(2);
		List<QuestionDto> questions = new ArrayList<>();
		when(questionService.displayQuestions()).thenReturn(questions);
		mockMvc.perform(get("/deletequestion").param("qNo", "2")).andExpect(status().isOk())
				.andExpect(view().name("questionpage")).andExpect(model().attribute("listOfQuestions", questions))
				.andExpect(model().attribute("deletequestionmessage", "Question Deleted Succesfully..."));
	}

	@Test
	void testDeleteQuestionWithException() throws Exception {
		doThrow(QuestionException.class).when(questionService).removeQuestion(2);
		mockMvc.perform(get("/deletequestion").param("qNo", "2")).andExpect(status().isOk())
				.andExpect(view().name("questionpage"))
				.andExpect(model().attribute("cannotdelete", "Cannot delete this question..."));
	}

	@Test
	void testUpdateQuestionPage() throws Exception {
		QuestionDto question = new QuestionDto();
		List<String> options = new ArrayList<>();
		options.add("hello");
		Set<Integer> answers = new TreeSet<>();
		answers.add(1);
		question.setOptions(options);
		question.setAnswers(answers);
		question.setId(2);
		when(questionService.getQuestion(2)).thenReturn(question);
		mockMvc.perform(get("/updatequestion").param("qNo", "2")).andExpect(status().isOk())
				.andExpect(view().name("updatequestion")).andExpect(model().attribute("question", question))
				.andExpect(model().attribute("options", "hello")).andExpect(model().attribute("answers", "1"));
	}

	@Test
	void testUpdatingQuestion() throws Exception {
		QuestionDto questionDto = new QuestionDto();
		List<String> options = new ArrayList<>();
		options.add("hello");
		Set<Integer> answers = new TreeSet<>();
		answers.add(1);
		questionDto.setOptions(options);
		questionDto.setAnswers(answers);
		questionDto.setId(2);
		List<QuestionDto> questions = new ArrayList<>();
		questions.add(new QuestionDto());
		when(questionService.displayQuestions()).thenReturn(questions);
		when(questionService.updateQuestion(questionDto)).thenReturn(questionDto);
		mockMvc.perform(get("/updatequestionwithdetails").param("id", "2")).andExpect(status().isOk())
				.andExpect(view().name("questionpage")).andExpect(model().attribute("listOfQuestions", questions))
				.andExpect(model().attribute("updatequestion", "2 Question updated succesfully..."));
	}
}
