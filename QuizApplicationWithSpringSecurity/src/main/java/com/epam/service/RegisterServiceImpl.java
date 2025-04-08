package com.epam.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.jparepository.UserRepository;
import com.epam.model.User;

@Service
@Primary
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	private Logger logger = LoggerFactory.getLogger("RegisterServiceImpl.class");

	@Override
	public UserDto addUser(UserDto userDto) throws UserException {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			logger.error("RegisterService:Cannot register as User already exists with specified username..");
			throw new UserException("Duplicate user found..");
		}
		logger.info("RegisterService:addUser Method..");
		return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
	}
}
