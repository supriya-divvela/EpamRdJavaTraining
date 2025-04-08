package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.exception.DuplicateUserException;
import com.epam.jparepository.UserRepository;
import com.epam.model.User;

@Service
@Primary
public class RegisterServiceImplementation implements RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addUser(UserDto userDto) throws DuplicateUserException {
		if (userRepository.existsByusername(userDto.getUsername())) {
			throw new DuplicateUserException("Duplicate User Found Exception...");
		}
		return userRepository.save(modelMapper.map(userDto, User.class)).getUsername();
	}
}
