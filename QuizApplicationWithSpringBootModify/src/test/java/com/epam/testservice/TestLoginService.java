package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.model.User;
import com.epam.repository.UserDao;
import com.epam.service.LoginServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestLoginService {
	@Mock
	private UserDao userDao;
	@InjectMocks
	private LoginServiceImplementation loginServiceImplementation;
	private List<User> users;

	@BeforeEach
	public void createUsers() {
		users = new ArrayList<>(Arrays.asList(new User("virat", "1234", "user"), new User("klrahul", "1234", "admin")));
	}

	@Test
	void testExistingUserLogin() {
		when(userDao.getUserData()).thenReturn(users);
		assertTrue(loginServiceImplementation.getAuthentication(new User("virat","1234","user")));
	}

	@Test
	void testNewUserLogin() {
		when(userDao.getUserData()).thenReturn(users);
		assertFalse(loginServiceImplementation.getAuthentication(new User("anushka","1234","user")));
	}

	@Test
	void testExistingAdminLogin() {
		when(userDao.getUserData()).thenReturn(users);
		assertTrue(loginServiceImplementation.getAuthentication(new User("klrahul", "1234", "admin")));
	}

	@Test
	void testNewAdminLogin() {
		when(userDao.getUserData()).thenReturn(users);
		assertFalse(loginServiceImplementation.getAuthentication(new User("anushka","1234","admin")));
	}

}
