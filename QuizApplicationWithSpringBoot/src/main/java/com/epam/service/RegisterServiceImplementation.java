package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.model.User;
import com.epam.repository.UserDao;

@Service
public class RegisterServiceImplementation implements RegisterService {
//	@Autowired
	private UserDao userDao;
//	@Autowired
	private LoginServiceImplementation loginServiceImplementation;

	@Autowired
	public RegisterServiceImplementation(UserDao userDao, LoginServiceImplementation loginServiceImplementation) {
		this.userDao = userDao;
		this.loginServiceImplementation = loginServiceImplementation;
	}

	@Override
	public boolean addUser(User user) {
		boolean addedUser = false;
		if (!loginServiceImplementation.getAuthentication(user)) {
			addedUser = true;
			userDao.addUser(user);
		}
		return addedUser;
	}
}
