package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.epam.controller.LibraryController;
import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.model.LibraryResponse;
import com.epam.service.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibraryController.class)
class TestLibraryController {
	@MockBean
	private LibraryService libraryService;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private RestTemplate restTemplate;

	@Test
	void testGetAllBooks() throws Exception {
		BookDto[] books = new BookDto[10];
		when(restTemplate.getForObject("http://localhost:1000/books", BookDto[].class)).thenReturn(books);
		mockMvc.perform(get("/library/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(restTemplate).getForObject("http://localhost:1000/books", BookDto[].class);
	}

	@Test
	void testGetBook() throws Exception {
		BookDto bookDto = new BookDto();
		when(restTemplate.getForEntity("http://localhost:1000/books/" + 1, BookDto.class))
				.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		mockMvc.perform(get("/library/books/{bookId}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(restTemplate).getForEntity("http://localhost:1000/books/" + 1, BookDto.class);

	}

	@Test
	void testAddBook() throws Exception {
		BookDto bookDto = new BookDto("oko", "hi", "ji", 5);
		when(restTemplate.postForEntity("http://localhost:1000/books", bookDto, BookDto.class))
				.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isCreated());
		verify(restTemplate).postForEntity("http://localhost:1000/books", bookDto, BookDto.class);
	}

	@Test
	void testAddBookWithMethodArgumentNotValidException() throws Exception {
		BookDto bookDto = new BookDto("oko", "hi", "", 5);
		mockMvc.perform(post("/library/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isBadRequest());
	}

	@Test
	void testUpdateBook() throws Exception {
		BookDto bookDto = new BookDto("oko", "hi", "ji", 5);
		doNothing().when(restTemplate).put("http://localhost:1000/books/" + 1, bookDto);
		when(restTemplate.getForEntity("http://localhost:1000/books/" + 1, BookDto.class))
				.thenReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.OK));
		mockMvc.perform(put("/library/books/{bookId}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpect(status().isOk());
		verify(restTemplate).getForEntity("http://localhost:1000/books/" + 1, BookDto.class);
	}

	@Test
	void testDeleteBook() throws Exception {
		doNothing().when(restTemplate).delete("http://localhost:1000/books/" + 1);
		mockMvc.perform(delete("/library/books/{bookId}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		verify(restTemplate).delete("http://localhost:1000/books/" + 1);
	}

	@Test
	void testGetAllUsers() throws Exception {
		UserDto[] users = new UserDto[10];
		when(restTemplate.getForObject("http://localhost:2000/users", UserDto[].class)).thenReturn(users);
		mockMvc.perform(get("/library/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(restTemplate).getForObject("http://localhost:2000/users", UserDto[].class);
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
		when(restTemplate.postForEntity("http://localhost:2000/users", userDto, UserDto.class))
				.thenReturn(new ResponseEntity<>(userDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isCreated());
		verify(restTemplate).postForEntity("http://localhost:2000/users", userDto, UserDto.class);
	}

	@Test
	void testUpdateUser() throws Exception {
		UserDto userDto = new UserDto("user", "user@gmail.com", "okok");
		doNothing().when(restTemplate).put("http://localhost:2000/users" + "user", userDto, UserDto.class);
		when(restTemplate.getForEntity("http://localhost:2000/users/" + "user", UserDto.class))
				.thenReturn(new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED));
		mockMvc.perform(put("/library/users/{username}", "user").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isAccepted());
		verify(restTemplate).getForEntity("http://localhost:2000/users/" + "user", UserDto.class);
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
