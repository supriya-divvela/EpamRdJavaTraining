package com.epam.service;

import com.epam.model.User;
import com.epam.repository.UserDao;

public class LoginServiceImplementation implements LoginService {
	private UserDao userDao;

	public LoginServiceImplementation(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean getAuthentication(User user) {
		boolean verify = false;
		if (user.getUsertype().equals("user") || user.getUsertype().equals("admin")) {
			verify = validate(user);
		}
		return verify;
	}

	@Override
	public boolean validate(User user) {
		return userDao.getUserData().stream().filter(userData -> userData.getUsername().equals(user.getUsername())
				&& userData.getPassword().equals(user.getPassword())).count() == 1;
	}
}
