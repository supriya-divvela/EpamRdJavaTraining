package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.FeignLibraryController;
import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.feign.BookFeignClient;
import com.epam.feign.UserFeignClient;
import com.epam.model.LibraryResponse;
import com.epam.service.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(FeignLibraryController.class)
class TestFeignController {
	@MockBean
	private LibraryService libraryService;
	@MockBean
	private BookFeignClient bookFeignClient;
	@MockBean
	private UserFeignClient userFeignClient;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllBooks() throws Exception {
		List<BookDto> books=new ArrayList<>();
		Mockito.when(bookFeignClient.getAllBooks())
				.thenReturn(new ResponseEntity<>(books, HttpStatus.OK));
		mockMvc.perform(get("/library/books")).andExpect(status().isOk()).andReturn();
		Mockito.verify(bookFeignClient).getAllBooks();
	}
	
	@Test
	void testGetBook() throws Exception {
		BookDto book=new BookDto();
		Mockito.when(bookFeignClient.getBook(1))
				.thenReturn(new ResponseEntity<>(book, HttpStatus.OK));
		mockMvc.perform(get("/library/books/{bookId}",1)).andExpect(status().isOk());
		Mockito.verify(bookFeignClient).getBook(1);
	}
	
	@Test
	void testAddBook() throws Exception {
		BookDto bookDto = new BookDto("oko", "hi", "ji", 5);
		Mockito.when(bookFeignClient.addBook(bookDto))
				.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isCreated()).andReturn();
		Mockito.verify(bookFeignClient).addBook(bookDto);
	}
	@Test
	void testUpdateBook() throws Exception {
		BookDto bookDto = new BookDto("oko", "hi", "ji", 5);
		Mockito.when(bookFeignClient.updateBook(bookDto,0))
		.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		mockMvc.perform(put("/library/books/{bookId}", 0).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isOk());
		verify(bookFeignClient).updateBook(bookDto, 0);
	}

	@Test
	void testDeleteBook() throws Exception {
		doNothing().when(libraryService).deleteAllByBookIds(1);
		mockMvc.perform(delete("/library/books/{bookId}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		verify(libraryService).deleteAllByBookIds(1);
	}
	@Test
	void testGetAllUsers() throws Exception {
		List<UserDto> userDtos=new ArrayList<>();
		when(userFeignClient.getAllUsers()).thenReturn(userDtos);
		mockMvc.perform(get("/library/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(userFeignClient).getAllUsers();
	}

	@Test
	void testGetUser() throws Exception {
		LibraryResponse libraryResponse = LibraryResponse.builder().build();
		when(libraryService.getUsersWithIssuedBooks("hii")).thenReturn(libraryResponse);
		mockMvc.perform(get("/library/users/{username}", "hii").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(libraryService).getUsersWithIssuedBooks("hii");
	}
	@Test
	void testAddUser() throws Exception {
		UserDto userDto = new UserDto("user", "user@gmail.com", "okok");
		Mockito.when(userFeignClient.addUser(userDto)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isCreated());
		verify(userFeignClient).addUser(userDto);
	}

	@Test
	void testUpdateUser() throws Exception {
		UserDto userDto = new UserDto("user", "user@gmail.com", "okok");
		Mockito.when(userFeignClient.updateUser("user",userDto))
		.thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		mockMvc.perform(put("/library/users/{username}", "user").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isOk());
		verify(userFeignClient).updateUser("user", userDto);
	}

	@Test
	void testDeleteUser() throws Exception {
		doNothing().when(libraryService).deleteUserWithAllBooks("hii");
		mockMvc.perform(delete("/library/users/{username}", "hii").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		verify(libraryService).deleteUserWithAllBooks("hii");
	}

	@Test
	void testIssueBook() throws Exception {
		LibraryDto libraryDto = new LibraryDto(1, "hii", 2);
		when(libraryService.issueBook("hii", 2)).thenReturn(libraryDto);
		mockMvc.perform(
				post("/library/users/{username}/books/{bookId}", "hii", 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testReceiveBook() throws Exception {
		doNothing().when(libraryService).receiveBook("hii", 2);
		mockMvc.perform(
				delete("/library/users/{username}/books/{bookId}", "hii", 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		verify(libraryService).receiveBook("hii", 2);
	}
}
