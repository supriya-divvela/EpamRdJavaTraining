package com.epam.testrestcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.restcontroller.UserApi;
import com.epam.service.LoginService;
import com.epam.service.RegisterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserApi.class)
class TestUserApi {
	@MockBean
	private RegisterService registerService;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private LoginService loginService;

	@Test
	void testAddUser() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto("username", "User@1234", "user");
		when(registerService.addUser(Mockito.any(UserDto.class))).thenReturn(userDto);
		mockMvc.perform(post("/userspage/user").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isCreated());
	}

	@Test
	void testAddUserWithException() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto("username", "User@1234", "user");
		when(registerService.addUser(Mockito.any(UserDto.class))).thenThrow(UserException.class);
		mockMvc.perform(post("/userspage/user").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isOk());
	}

	@Test
	void testVerifyUserOnSuccessAuthentication() throws Exception {
		UserDto userDto = new UserDto("username", "User@1234", "user");
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(true);
		mockMvc.perform(get("/userspage/verify").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isOk());
	}

	@Test
	void testVerifyUserOnFailureAuthentication() throws Exception {
		UserDto userDto = new UserDto("username", "User@1234", "user");
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(false);
		mockMvc.perform(get("/userspage/verify").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isUnauthorized());
	}
}
