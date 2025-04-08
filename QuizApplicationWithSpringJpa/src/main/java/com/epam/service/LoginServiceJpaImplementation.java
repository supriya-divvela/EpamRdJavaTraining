package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.jparepository.UserRepository;

@Service
@Primary
public class LoginServiceJpaImplementation implements LoginService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean getAuthentication(UserDto userDto) {
		return Optional.ofNullable(userDto).map(this::validate).orElse(false);
	}

	@Override
	public boolean validate(UserDto userDto) {
		return userRepository.existsByUsernameAndPasswordAndUsertype(userDto.getUsername(), userDto.getPassword(),
				userDto.getUsertype());
	}
}
