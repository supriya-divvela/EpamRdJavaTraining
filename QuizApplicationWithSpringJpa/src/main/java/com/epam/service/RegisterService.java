package com.epam.service;

import com.epam.dto.UserDto;
import com.epam.exception.DuplicateUserException;

public interface RegisterService {
	String addUser(UserDto userDto) throws DuplicateUserException;
}
