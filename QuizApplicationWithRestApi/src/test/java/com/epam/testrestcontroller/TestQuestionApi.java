package com.epam.testrestcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionException;
import com.epam.restcontroller.QuestionApi;
import com.epam.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(QuestionApi.class)
class TestQuestionApi {

	@MockBean
	private QuestionService questionService;
	@Autowired
	private MockMvc mockMvc;
	@Test
	void testGetQuestions() throws Exception {
		List<QuestionDto> questions = new ArrayList<>();
		when(questionService.displayQuestions()).thenReturn(questions);
		mockMvc.perform(get("/questionspage/questions").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testGetQuestion() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(1);
		when(questionService.getQuestion(1)).thenReturn(questionDto);
		mockMvc.perform(get("/questionspage/questions/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
	}

	@Test
	void testGetQuestionWithMethodArgumentTypeMismatchException() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(1);
		when(questionService.getQuestion(1)).thenReturn(questionDto);
		mockMvc.perform(get("/questionspage/questions/{id}", "ab").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testGetQuestionWithEntityNotFoundException() throws JsonProcessingException, Exception {
		when(questionService.getQuestion(1)).thenThrow(EntityNotFoundException.class);
		mockMvc.perform(get("/questionspage/questions/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetQuestionWithException() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(1);
		when(questionService.getQuestion(1)).thenThrow(QuestionException.class);
		mockMvc.perform(get("/questionspage/questions/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testAddQuestion() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto(1, "What is java", List.of("a", "b", "c", "d"), "easy", "java",
				Set.of(1, 2), 2);
		when(questionService.addQuestion(Mockito.any(QuestionDto.class))).thenReturn(questionDto);
		mockMvc.perform(post("/questionspage/questions").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(questionDto))).andExpect(status().isCreated());
	}

	@Test
	void testAddQuestionWithNullValue() throws JsonProcessingException, Exception {
		QuestionDto questionDto = null;
		mockMvc.perform(post("/questionspage/questions").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(questionDto)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void testAddQuestionWithMethodArgumentNotValidException() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto(1, "What is java", List.of("a"), "easiest", "java", Set.of(1, 2),
				987);
		when(questionService.addQuestion(Mockito.any(QuestionDto.class))).thenReturn(questionDto);
		mockMvc.perform(post("/questionspage/questions").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(questionDto))).andExpect(status().isBadRequest());
	}

	@Test
	void testDeleteQuestion() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(1);
		doNothing().when(questionService).removeQuestion(1);
		mockMvc.perform(delete("/questionspage/questions/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	void testDeleteQuestionWithDataIntegrityException() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(1);
		doThrow(DataIntegrityViolationException.class).when(questionService).removeQuestion(1);
		mockMvc.perform(delete("/questionspage/questions/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testUpdateQuestion() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto(1, "What is java", List.of("a", "b", "c", "d"), "easy", "java",
				Set.of(1, 2), 2);
		when(questionService.updateQuestion(Mockito.any(QuestionDto.class))).thenReturn(questionDto);
		mockMvc.perform(put("/questionspage/questions").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(questionDto))).andExpect(status().isAccepted());
	}

	@Test
	void testUpdateQuestionWithException() throws JsonProcessingException, Exception {
		QuestionDto questionDto = new QuestionDto(1, "What is java", List.of("a", "b", "c", "d"), "easy", "java",
				Set.of(1, 2), 2);
		when(questionService.updateQuestion(Mockito.any(QuestionDto.class))).thenThrow(QuestionException.class);
		mockMvc.perform(put("/questionspage/questions").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(questionDto))).andExpect(status().isOk());
	}
}
