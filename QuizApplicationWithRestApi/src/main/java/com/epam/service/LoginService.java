package com.epam.service;

import com.epam.dto.UserDto;

public interface LoginService {
	boolean getAuthentication(UserDto user);
}
