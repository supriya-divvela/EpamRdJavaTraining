package com.epam.service;

import java.util.List;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;

public interface UserService {
	UserDto addUser(UserDto userDto) throws UserException;

	void deleteUser(String username);

	List<UserDto> getAllUsers();

	UserDto getUser(String username) throws UserException;

	UserDto updateUser(String username,UserDto userDto) throws UserException;
}
