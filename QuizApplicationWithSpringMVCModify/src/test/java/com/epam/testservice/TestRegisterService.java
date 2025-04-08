package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.UserDto;
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
		UserDto userDto = new UserDto("anushka", "1234", "user");
		when(userDao.addUser(Mockito.any(User.class))).thenReturn("anushka");
		assertEquals("anushka", registerService.addUser(userDto));
	}

}