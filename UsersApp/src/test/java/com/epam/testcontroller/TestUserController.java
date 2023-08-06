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

import com.epam.controller.UserController;
import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class TestUserController {
	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllusers() throws Exception {
		List<UserDto> usersList = new ArrayList<>();
		when(userService.getAllUsers()).thenReturn(usersList);
		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetUser() throws Exception {
		UserDto userDto = new UserDto();
		when(userService.getUser("hii")).thenReturn(userDto);
		mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
//	@Test
//	void testGetUserWithMethodArgumentTypeMismatchException() throws JsonProcessingException, Exception {
//		UserDto UserDto = new UserDto(1,"supriya","supriya@gmail.com","");
//		when(userService.getUser("")).thenReturn(UserDto);
//		mockMvc.perform(get("/users/{id}", "ab").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest());
//	}

	@Test
	void testGetUserWithUserException() throws Exception {
		when(userService.getUser("hii")).thenThrow(UserException.class);
		mockMvc.perform(get("/users/hii").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testAddUser() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto("hii", "sup@gmail.com", "sup");
		when(userService.addUser(Mockito.any(UserDto.class))).thenReturn(userDto);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isCreated());

	}

	@Test
	void testAddUserWithDataIntegrityException() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto("hii", "sup@gmail.com", "sup");
		when(userService.addUser(Mockito.any(UserDto.class))).thenThrow(DataIntegrityViolationException.class);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isBadRequest());
	}

	@Test
	void testAddUserWithMethodArgumentNotValidException() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto("", "sup", "sup");
		when(userService.addUser(Mockito.any(UserDto.class))).thenReturn(userDto);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isBadRequest());

	}

	@Test
	void testAddUserWithRuntimeException() throws JsonProcessingException, Exception {
		UserDto userDto = null;
		when(userService.addUser(Mockito.any(UserDto.class))).thenReturn(userDto);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isInternalServerError());
	}

	@Test
	void testDeleteUser() throws JsonProcessingException, Exception {
		doNothing().when(userService).deleteUser("Hii");
		mockMvc.perform(delete("/users/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testUpdateUser() throws JsonProcessingException, Exception {
		UserDto userDto = UserDto.builder().username("hii").email("sup@gmail.com").name("sup").build();
		when(userService.updateUser("hii", userDto)).thenReturn(userDto);
		mockMvc.perform(put("/users/{username}", "hii").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isOk());
	}
}
