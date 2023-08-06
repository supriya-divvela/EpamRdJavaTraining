package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.jparepository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean getAuthentication(UserDto userDto) {
		log.info("LoginService:getAuthentication");
		return userRepository.existsByUsernameAndPasswordAndRole(userDto.getUsername(), userDto.getPassword(),
				userDto.getRole());
	}
}
