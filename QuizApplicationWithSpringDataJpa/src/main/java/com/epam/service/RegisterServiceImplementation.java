package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.exception.DuplicateUserException;
import com.epam.jparepository.UserRepository;
import com.epam.model.User;

@Service
public class RegisterServiceImplementation implements RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String addUser(UserDto userDto) throws DuplicateUserException {
		return userRepository.save(new User(userDto.getUsername(), userDto.getPassword(), userDto.getUsertype()))
				.getUsername();
	}
}
