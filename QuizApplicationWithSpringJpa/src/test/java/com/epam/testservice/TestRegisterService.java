package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.UserDto;
import com.epam.exception.DuplicateUserException;
import com.epam.jparepository.UserRepository;
import com.epam.model.User;
import com.epam.service.LoginService;
import com.epam.service.RegisterServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestRegisterService {
	@Mock
	private UserRepository userRepository;
	@Mock
	private LoginService loginService;
	@InjectMocks
	private RegisterServiceImplementation registerService;
	@Mock
	private ModelMapper modelMapper;

	@Test
	void testNewUserRegister() throws DuplicateUserException {
		User user = new User("anushka", "1234", "user");
		UserDto userDto = new UserDto("anushka", "1234", "user");
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		assertEquals("anushka", registerService.addUser(userDto));
	}
	
	@Test
	void testAlreadyRegisteredUser() throws DuplicateUserException {
		UserDto userDto = new UserDto("anushka", "1234", "user");
		when(userRepository.existsByusername(userDto.getUsername())).thenReturn(true);
		assertThrows(DuplicateUserException.class,()-> registerService.addUser(userDto));
	}

}