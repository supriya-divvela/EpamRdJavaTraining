package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.exception.UserException;
import com.epam.model.User;
import com.epam.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto addUser(UserDto userDto) throws UserException {
		log.info("UserService:Add User Method..");
		return modelMapper.map(userRepository.save(User.builder().email(userDto.getEmail()).name(userDto.getName())
				.username(userDto.getUsername()).build()), UserDto.class);
	}
	@Transactional
	@Override
	public void deleteUser(String username) {
		log.info("UserService:Delete User Method...");
		userRepository.deleteByUsername(username);
	}

	@Override
	public List<UserDto> getAllUsers() {
		log.info("UserService:Get All Users Method..");
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
	}

	@Override
	public UserDto getUser(String username) throws UserException {
		log.info("UserService:Get User Method..");
		return userRepository.findByUsername(username).map(user -> modelMapper.map(user, UserDto.class))
				.orElseThrow(() -> new UserException("User not found with this username.."));
	}

	@Override
	public UserDto updateUser(String username, UserDto userDto) throws UserException {
		log.info("UserService:Update User Method..");
		return modelMapper.map(userRepository.findByUsername(userDto.getUsername())
				.map(user -> userRepository.save(User.builder().id(user.getId()).username(userDto.getUsername())
						.email(userDto.getEmail()).name(userDto.getName()).build()))
				.orElseThrow(() -> new UserException("User not found with this username..")), UserDto.class);
	}
}
