package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.repository.UserDao;

@Service
public class RegisterServiceImplementation implements RegisterService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginServiceImplementation loginServiceImplementation;

	@Override
	public boolean addUser(UserDto userDto) {
		boolean addedUser = false;
		if (!loginServiceImplementation.getAuthentication(userDto)) {
			addedUser = true;
			userDao.addUser(userDto);
		}
		return addedUser;
	}
}
