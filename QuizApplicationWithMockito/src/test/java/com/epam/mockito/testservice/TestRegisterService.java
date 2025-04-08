package com.epam.mockito.testservice;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.model.User;
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
		User user = new User("anushka", "1234", "user");
		when(loginServiceImplementation.getAuthentication(user)).thenReturn(false);
		assertTrue(registerServiceImplementation.addUser(user));
	}

	@Test
	void testNewAdminRegister() {
		User user = new User("dhanasree", "1234", "admin");
		when(loginServiceImplementation.getAuthentication(user)).thenReturn(false);
		assertTrue(registerServiceImplementation.addUser(user));
	}

}