package com.epam.service;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;

public interface RegisterService {
	UserDto addUser(UserDto userDto) throws UserException;
}
