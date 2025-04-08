package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.exception.DuplicateUserException;
import com.epam.model.User;
import com.epam.repository.UserDaoImplementation;
import com.epam.service.LoginService;
import com.epam.service.RegisterServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestRegisterService {
	@Mock
	private UserDaoImplementation userDao;
	@Mock
	private LoginService loginService;
	@InjectMocks
	private RegisterServiceImplementation registerService;

	@Test
	void testNewUserRegister() throws DuplicateUserException {
		User user = new User("anushka", "1234", "user");
		when(userDao.findUser(user)).thenReturn(true);
		when(userDao.addUser(user)).thenReturn(user.getUsername());
		registerService.addUser(user);
	}

	@Test
	void testNewAdminRegister() {
		User user = new User("dhanasree", "1234", "admin");
		when(loginService.getAuthentication(user)).thenReturn(false);
//		assertTrue(registerService.addUser(user));
	}

}