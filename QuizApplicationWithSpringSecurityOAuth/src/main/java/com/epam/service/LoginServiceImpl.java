package com.epam.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.jparepository.UserRepository;

@Service
@Primary
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepository userRepository;
	private Logger logger=LoggerFactory.getLogger("LoginServiceImpl.class");
	@Override
	public boolean getAuthentication(UserDto userDto) {
		logger.info("LoginService:getAuthentication");
		return Optional.ofNullable(userDto).map(this::validate).orElse(false);
	}

	@Override
	public boolean validate(UserDto userDto) {
		logger.info("LoginService:validate");
		return userRepository.existsByUsernameAndPasswordAndRole(userDto.getUsername(), userDto.getPassword(),
				userDto.getRole());
	}
}
