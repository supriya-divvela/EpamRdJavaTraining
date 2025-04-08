package com.epam.testrestcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.exception.QuizException;
import com.epam.restcontroller.QuizApi;
import com.epam.service.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(QuizApi.class)
class TestQuizApi {
	@MockBean
	private QuizService quizService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetQuizes() throws Exception {
		List<QuizDto> quizes = new ArrayList<>();
		when(quizService.displayAvaliableQuizes()).thenReturn(quizes);
		mockMvc.perform(get("/quizspage/quizs").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetQuestion() throws JsonProcessingException, Exception {
		QuizDto quizDto = new QuizDto();
		quizDto.setId(1);
		when(quizService.getQuiz(1)).thenReturn(quizDto);
		mockMvc.perform(get("/quizspage/quizs/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
	}

	@Test
	void testGetQuizWithException() throws JsonProcessingException, Exception {
		QuizDto quizDto = new QuizDto();
		quizDto.setId(1);
		when(quizService.getQuiz(1)).thenThrow(QuizException.class);
		mockMvc.perform(get("/quizspage/quizs/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testAddQuiz() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto(1, "What is java", List.of("a", "b", "c", "d"), "easy", "java",
				Set.of(1, 2), 2);
		QuizDto quizDto = new QuizDto("Quiz1", List.of(questionDto));
		when(quizService.createQuiz("Quiz1", List.of(1, 2, 3, 4))).thenReturn(quizDto);
		when(quizService.addQuiz(Mockito.any(QuizDto.class))).thenReturn(quizDto);
		mockMvc.perform(post("/quizspage/quizs").param("title", "Quiz1").param("listOfQuestions", "1,2,3,4")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	void testDeleteQuiz() throws JsonProcessingException, Exception {
		QuizDto quizDto = new QuizDto();
		quizDto.setId(1);
		doNothing().when(quizService).removeQuiz(1);
		mockMvc.perform(delete("/quizspage/quizs/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testUpdateQuiz() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto(1, "What is java", List.of("a", "b", "c", "d"), "easy", "java",
				Set.of(1, 2), 2);
		QuizDto quizDto = new QuizDto("Quiz1", List.of(questionDto));
		when(quizService.updateQuiz(Mockito.any(QuizDto.class))).thenReturn(quizDto);
		mockMvc.perform(put("/quizspage/quizs").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(quizDto))).andExpect(status().isAccepted());
	}

	@Test
	void testUpdateQuizWithException() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto(1, "What is java", List.of("a", "b", "c", "d"), "easy", "java",
				Set.of(1, 2), 2);
		QuizDto quizDto = new QuizDto("Quiz1", List.of(questionDto));
		when(quizService.updateQuiz(Mockito.any(QuizDto.class))).thenThrow(QuizException.class);
		mockMvc.perform(put("/quizspage/quizs").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(quizDto))).andExpect(status().isOk());
	}
}
