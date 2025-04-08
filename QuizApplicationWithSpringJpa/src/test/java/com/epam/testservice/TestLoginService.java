package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.UserDto;
import com.epam.jparepository.UserRepository;
import com.epam.service.LoginServiceJpaImplementation;

@ExtendWith(MockitoExtension.class)
class TestLoginService {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private LoginServiceJpaImplementation loginServiceJpaImplementation;

	@Test
	void testExistingUserLogin() {
		when(userRepository.existsByUsernameAndPasswordAndUsertype("virat","1234","user")).thenReturn(true);
		assertTrue(loginServiceJpaImplementation.getAuthentication(new UserDto("virat", "1234", "user")));
	}

	@Test
	void testNewUserLogin() {
		when(userRepository.existsByUsernameAndPasswordAndUsertype("anushka","1234","user")).thenReturn(false);
		assertFalse(loginServiceJpaImplementation.getAuthentication(new UserDto("anushka","1234","user")));
	}

	@Test
	void testExistingAdminLogin() {
		when(userRepository.existsByUsernameAndPasswordAndUsertype("klrahul","1234","admin")).thenReturn(true);
		assertTrue(loginServiceJpaImplementation.getAuthentication(new UserDto("klrahul", "1234", "admin")));
	}

	@Test
	void testNewAdminLogin() {
		when(userRepository.existsByUsernameAndPasswordAndUsertype("anushka","1234","admin")).thenReturn(false);
		assertFalse(loginServiceJpaImplementation.getAuthentication(new UserDto("anushka","1234","admin")));
	}

}
