package com.epam.testservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestUserService {
	@Mock
	private UserRepository userRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testAddUser() throws UserException {
		User user=User.builder().email("hii").username("java").name("ok").build();
		UserDto userDto=UserDto.builder().email("hii").username("java").name("ok").build();
		when(userRepository.save(user)).thenReturn(user);
		userService.addUser(userDto);
		verify(userRepository).save(user);
	}

	@Test
	void testDeleteUser() {
		doNothing().when(userRepository).deleteByUsername("okok");;
		userService.deleteUser("okok");
		verify(userRepository).deleteByUsername("okok");
	}

	@Test
	void testGetAllUsers() {
		User user = new User();
		user.setId(1);
		UserDto userDto = new UserDto();
		List<User> usersList = new ArrayList<>();
		usersList.add(user);
		when(userRepository.findAll()).thenReturn(usersList);
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
		assertEquals(1, userService.getAllUsers().size());

	}
	@Test
	void testGetUser() throws UserException {
		User user=new User();
		user.setId(1);
		UserDto userDto=new UserDto();
		when(userRepository.findByUsername("okok")).thenReturn(Optional.of(user));
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
		assertEquals(userDto, userService.getUser("okok"));
	}
	
	@Test
	void testGetUserWithException() throws UserException {
		when(userRepository.findByUsername("okok")).thenReturn(Optional.empty());
		assertThrows(UserException.class,()->userService.getUser("okok"));
	}
	@Test
	void testUpdateUser() throws UserException {
		User user=User.builder().email("hii").username("java").name("ok").build();
		UserDto userDto=UserDto.builder().email("hii").username("java").name("ok").build();
		when(userRepository.findByUsername("java")).thenReturn(Optional.of(user));
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
		when(userRepository.save(user)).thenReturn(user);
		userService.updateUser("java",userDto);
		verify(userRepository).save(user);
	}
	@Test
	void testUpdateUserWithException() throws UserException {
		when(userRepository.findByUsername("okok")).thenReturn(Optional.empty());
		UserDto userDto=new UserDto("okok","ok","ok");
		assertThrows(UserException.class,()->userService.updateUser("okok",userDto));
	}
}
