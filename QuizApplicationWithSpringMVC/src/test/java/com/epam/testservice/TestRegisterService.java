package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.UserDto;
import com.epam.repository.UserDao;
import com.epam.service.LoginServiceImplementation;
import com.epam.service.RegisterServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestRegisterService {
	@Mock
	private UserDao userDao;
	@Mock
	private LoginServiceImplementation loginServiceImplementation;
	@InjectMocks
	private RegisterServiceImplementation registerServiceImplementation;

	@Test
	void testNewUserRegister() {
		UserDto user = new UserDto("anushka", "1234", "user");
		when(loginServiceImplementation.getAuthentication(user)).thenReturn(false);
		assertTrue(registerServiceImplementation.addUser(user));
	}

	@Test
	void testNewAdminRegister() {
		UserDto user = new UserDto("dhanasree", "1234", "admin");
		when(loginServiceImplementation.getAuthentication(user)).thenReturn(false);
		assertTrue(registerServiceImplementation.addUser(user));
	}

}