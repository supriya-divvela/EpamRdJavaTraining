package com.epam.testcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.AdminController;
import com.epam.dto.UserDto;
import com.epam.service.LoginService;
import com.epam.service.RegisterService;

@WebMvcTest(AdminController.class)
class TestAdminController {
	@MockBean
	private LoginService loginService;
	@MockBean
	private RegisterService registerService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testHome() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
	void testIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	void testAdminRegister() throws Exception {
		mockMvc.perform(get("/adminregister")).andExpect(status().isOk()).andExpect(view().name("adminregister"));
	}

	@Test
	void testLogout() throws Exception {
		mockMvc.perform(get("/logout")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	void testAdminLogin() throws Exception {
		mockMvc.perform(get("/adminlogin")).andExpect(status().isOk()).andExpect(view().name("adminlogin"));
	}

	@Test
	void testAdminHome() throws Exception {
		mockMvc.perform(get("/adminhome")).andExpect(status().isOk()).andExpect(view().name("adminhome"));
	}

	@Test
	void testverifyAdmin() throws Exception {
		UserDto userDto = new UserDto("virat", "anushka", "admin");
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(true);
		mockMvc.perform(get("/verifyadmin").param("username", userDto.getUsername())
				.param("password", userDto.getPassword()).param("usertype", userDto.getRole()))
				.andExpect(status().isOk()).andExpect(view().name("adminhome"))
				.andExpect(model().attribute("login", "Admin Logged in succesfully......"));
	}

	@Test
	void testverifyAdminFailCase() throws Exception {
		UserDto userDto = new UserDto("virat", "anushka", "admin");
		when(loginService.getAuthentication(userDto)).thenReturn(true);
		mockMvc.perform(get("/verifyadmin").param("username", userDto.getUsername())
				.param("password", userDto.getPassword()).param("usertype", userDto.getRole()))
				.andExpect(status().isOk()).andExpect(view().name("adminlogin"))
				.andExpect(model().attribute("invaliduser", "Invalid User..."));
	}

	@Test
	void testAddDuplicateUser() throws Exception {
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(false);
		mockMvc.perform(post("/addadmin").param("username", "virat")
				.param("password", "anushka").param("confirmpassword", "anushka")).andExpect(status().isOk())
				.andExpect(view().name("adminhome")).andExpect(model().attribute("adminregisterd","admin registered succesfully..."));
	}

	@Test
	void testAddUser() throws Exception {
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(true);
		mockMvc.perform(
				post("/addadmin").param("username", "virat").param("password", "anushka").param("confirmpassword", "anushka"))
				.andExpect(view().name("adminregister")).andExpect(model().attribute("alreadyregistered",
						"admin already registered...please try adding another admin.."));
	}

	@Test
	void testAddUserWithPasswordMisMatch() throws Exception {
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(false);
		mockMvc.perform(
				post("/addadmin").param("username", "virat").param("password", "anushka").param("confirmpassword", "anushkasharma"))
				.andExpect(view().name("adminregister")).andExpect(model().attribute("adminregistered",
						"password and confirm password did not match..."));
	}
}
