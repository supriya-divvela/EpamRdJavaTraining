package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.jparepository.UserRepository;
import com.epam.model.User;
import com.epam.service.LoginService;
import com.epam.service.RegisterServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestRegisterService {
	@Mock
	private UserRepository userRepository;
	@Mock
	private LoginService loginService;
	@InjectMocks
	private RegisterServiceImpl registerService;
	@Mock
	private ModelMapper modelMapper;

	@Test
	void testNewUserRegister() throws UserException {
		User user = new User("anushka", "1234", "user");
		UserDto userDto = new UserDto("anushka", "1234", "user");
		when(userRepository.existsByUsername(userDto.getUsername())).thenReturn(false);
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		when(modelMapper.map(user,UserDto.class)).thenReturn(userDto);
		assertEquals(userDto, registerService.addUser(userDto));
	}
	
	@Test
	void testAlreadyRegisteredUser() throws UserException {
		UserDto userDto = new UserDto("anushka", "1234", "user");
		when(userRepository.existsByUsername(userDto.getUsername())).thenReturn(true);
		assertThrows(UserException.class,()-> registerService.addUser(userDto));
	}

}