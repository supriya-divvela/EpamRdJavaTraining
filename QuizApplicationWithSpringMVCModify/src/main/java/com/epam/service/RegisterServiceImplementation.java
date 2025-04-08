package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.exception.DuplicateUserException;
import com.epam.model.User;
import com.epam.repository.UserDaoImplementation;

@Service
public class RegisterServiceImplementation implements RegisterService {

	@Autowired
	private UserDaoImplementation userDao;
 
	@Override
	public String addUser(UserDto userDto) throws DuplicateUserException {
		return userDao.addUser(new User(userDto.getUsername(),userDto.getPassword(),userDto.getUsertype()));
	}
}
