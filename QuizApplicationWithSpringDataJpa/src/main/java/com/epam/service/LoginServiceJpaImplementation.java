package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.jparepository.UserRepository;

@Service
public class LoginServiceJpaImplementation implements LoginService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean getAuthentication(UserDto userDto) {
		return Optional.ofNullable(userDto).map(this::validate).orElse(false);
	}

	@Override
	public boolean validate(UserDto user) {
		return userRepository.findAll().stream().filter(userData -> userData.getUsername().equals(user.getUsername())
				&& userData.getPassword().equals(user.getPassword()) && userData.getUsertype().equals(user.getUsertype())).count() > 0;
	}
}
