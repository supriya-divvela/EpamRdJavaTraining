package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DuplicateUserException;
import com.epam.model.User;
import com.epam.repository.UserDao;

@Service
public class RegisterServiceImplementation implements RegisterService {
	@Autowired
	private UserDao userDao;

	@Override
	public String addUser(User user) throws DuplicateUserException {
		if (!userDao.findUser(user)) {
			throw new DuplicateUserException();
		}
		return userDao.addUser(user);
	}
}
