package com.epam.testcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.UserController;
import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.dto.UserDto;
import com.epam.service.LoginService;
import com.epam.service.QuizService;
import com.epam.service.RegisterService;
import com.epam.service.UserService;

@WebMvcTest(UserController.class)
class TestUserController {
	@MockBean
	private QuizService quizService;
	@MockBean
	private LoginService loginService;
	@MockBean
	private RegisterService registerService;
	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testDisplayQuizes() throws Exception {
		List<QuizDto> quizes = new ArrayList<>();
		when(quizService.displayAvaliableQuizes()).thenReturn(quizes);
		mockMvc.perform(get("/user")).andExpect(status().isOk()).andExpect(view().name("user"))
				.andExpect(model().attribute("listOfQuizs", quizes));
	}

	@Test
	void testAttemptQuiz() throws Exception {
		List<QuestionDto> questions = new ArrayList<>();
		QuizDto quiz = new QuizDto();
		quiz.setId(2);
		quiz.setListOfQuestions(questions);
		when(quizService.getQuiz(2)).thenReturn(quiz);
		mockMvc.perform(get("/attemptquiz").param("quizNo", "2")).andExpect(status().isOk())
				.andExpect(view().name("attemptquiz")).andExpect(model().attribute("quizNo", 2))
				.andExpect(model().attribute("questions", questions));
	}

	@Test
	void testUserLoginPage() throws Exception {
		mockMvc.perform(get("/userlogin")).andExpect(status().isOk()).andExpect(view().name("userlogin"));
	}

	@Test
	void testUserRegisterPage() throws Exception {
		mockMvc.perform(get("/userregister")).andExpect(status().isOk()).andExpect(view().name("userregister"));
	}

	@Test
	void testverifyUserFailCase() throws Exception {
		UserDto userDto = new UserDto("virat", "anushka", "admin");
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(false);
		mockMvc.perform(get("/verifyuser").param("username", userDto.getUsername())
				.param("password", userDto.getPassword()).param("usertype", userDto.getRole()))
				.andExpect(status().isOk()).andExpect(view().name("userlogin"));
	}

	@Test
	void testverifyUser() throws Exception {
		UserDto userDto = new UserDto("virat", "anushka", "admin");
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(true);
		mockMvc.perform(get("/verifyuser").param("username", userDto.getUsername())
				.param("password", userDto.getPassword()).param("usertype", userDto.getRole()))
				.andExpect(status().isOk()).andExpect(view().name("user"));
	}

	@Test
	void testAddUser() throws Exception {
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(false);
		mockMvc.perform(post("/register").param("username", "virat")
				.param("password", "anushka").param("confirmpassword", "anushka")).andExpect(status().isOk())
				.andExpect(view().name("userlogin")).andExpect(model().attribute("userregisterd","user registered succesfully..."));
	}

	@Test
	void testAddDuplicateUser() throws Exception {
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(true);
		mockMvc.perform(
				post("/register").param("username", "virat").param("password", "anushka").param("confirmpassword", "anushka"))
				.andExpect(view().name("userlogin")).andExpect(model().attribute("alreadyregistered","User already registered...please try login"));
	}

	@Test
	void testAddUserWithPasswordMisMatch() throws Exception {
		when(loginService.getAuthentication(Mockito.any(UserDto.class))).thenReturn(false);
		mockMvc.perform(
				post("/register").param("username", "virat").param("password", "anushka").param("confirmpassword", "anushkasharma"))
				.andExpect(view().name("userregister")).andExpect(model().attribute("passwordincorrect",
						"password and confirm password did not match..."));
	}

	@Test
	void testMarks() throws Exception {
		QuestionDto question = new QuestionDto();
		question.setAnswers(new TreeSet<>(List.of(1)));
		question.setOptions(List.of("hhii", "hello", "okoks"));
		question.setMarks(4);
		List<QuestionDto> questions = new ArrayList<>();
		questions.add(question);
		QuizDto quiz = new QuizDto();
		quiz.setListOfQuestions(questions);
		when(quizService.getQuiz(1)).thenReturn(quiz);
		when(userService.calculateMarks(List.of(List.of(1)), quizService.getQuiz(1))).thenReturn(4);
		when(userService.calculateTotalMarks(quizService.getQuiz(1))).thenReturn(4);
		mockMvc.perform(get("/marks").param("quizNo", "1").param("answers", "1")).andExpect(view().name("marks"))
				.andExpect(model().attribute("quizNo", 1)).andExpect(model().attribute("marksobtained", 4))
				.andExpect(model().attribute("totalmarks", 4));
	}

}
