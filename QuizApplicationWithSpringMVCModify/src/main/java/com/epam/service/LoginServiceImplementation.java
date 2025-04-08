package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.repository.UserDao;

@Service
public class LoginServiceImplementation implements LoginService {
	@Autowired
	private UserDao userDao;

	@Override
	public boolean getAuthentication(UserDto userDto) {
		return Optional.ofNullable(userDto).map(this::validate).orElse(false);
	}

	@Override
	public boolean validate(UserDto user) {
		return userDao.getUserData().stream().filter(userData -> userData.getUsername().equals(user.getUsername())
				&& userData.getPassword().equals(user.getPassword()) && userData.getUsertype().equals(user.getUsertype())).count() > 0;
	}
}
