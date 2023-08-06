package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.BookController;
import com.epam.dto.BookDto;
import com.epam.exception.BookException;
import com.epam.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
class TestBookController {
	@MockBean
	private BookService bookService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllBooks() throws Exception {
		List<BookDto> booksList = new ArrayList<>();
		when(bookService.getAllBooks()).thenReturn(booksList);
		mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetBook() throws Exception {
		BookDto bookDto = new BookDto();
		when(bookService.getBook(1)).thenReturn(bookDto);
		mockMvc.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetBookWithMethodArgumentTypeMismatchException() throws JsonProcessingException, Exception {
		BookDto bookDto = new BookDto("oko", "oko", "oko", 1);
		when(bookService.getBook(1)).thenReturn(bookDto);
		mockMvc.perform(get("/books/{id}", "ab").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testGetBookWithBookException() throws Exception {
//		BookDto bookDto=new BookDto();
		when(bookService.getBook(1)).thenThrow(BookException.class);
		mockMvc.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testAddBook() throws JsonProcessingException, Exception {
		BookDto bookDto = new BookDto("oko", "oko", "oko", 1);
		when(bookService.addBook(Mockito.any(BookDto.class))).thenReturn(bookDto);
		mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isCreated());

	}

	@Test
	void testAddBookWithMethodArgumentNotValidException() throws JsonProcessingException, Exception {
		BookDto bookDto = new BookDto("oko", "oko", "oko", -1);
		when(bookService.addBook(Mockito.any(BookDto.class))).thenReturn(bookDto);
		mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isBadRequest());

	}
	@Test
	void testAddBookWithDataIntegrityException() throws JsonProcessingException, Exception {
		BookDto bookDto = new BookDto("oko", "oko", "oko", 4);
		when(bookService.addBook(bookDto)).thenThrow(DataIntegrityViolationException.class);
		mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isBadRequest());

	}
	@Test
	void testAddBookWithRuntimeException() throws JsonProcessingException, Exception {
		BookDto bookDto = null;
		when(bookService.addBook(Mockito.any(BookDto.class))).thenReturn(bookDto);
		mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isInternalServerError());
	}

	@Test
	void testDeleteBook() throws JsonProcessingException, Exception {
		doNothing().when(bookService).deleteBook(1);
		mockMvc.perform(delete("/books/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testUpdateQuestion() throws JsonProcessingException, Exception {
		BookDto bookDto = new BookDto("oko", "oko", "oko", 1);
		when(bookService.updateBook(1, bookDto)).thenReturn(bookDto);
		mockMvc.perform(put("/books/{id}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isOk());
	}
}
