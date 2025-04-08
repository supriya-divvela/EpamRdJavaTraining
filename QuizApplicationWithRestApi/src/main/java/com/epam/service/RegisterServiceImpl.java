package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.jparepository.UserRepository;
import com.epam.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addUser(UserDto userDto) throws UserException {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new UserException("Duplicate user found..");
		}
		log.info("RegisterService:addUser Method..");
		return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
	}
}
