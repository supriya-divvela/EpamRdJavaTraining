package com.epam.service;

import com.epam.daorepository.UserDao;
import com.epam.model.User;

public class LoginService {
	private UserDao userDao = new UserDao();

	public boolean getAuthentication(User user) {
		boolean verify = false;
		if (user.getUsertype().equals("user")) {
			verify = userDao.getUserData().stream()
					.filter(userData -> userData.getUsername().equals(user.getUsername())
							&& userData.getPassword().equals(user.getPassword())
							&& userData.getUsertype().equals("user"))
					.count() == 1;
		} else {
			verify = userDao.getUserData().stream()
					.filter(userData -> userData.getUsername().equals(user.getUsername())
							&& userData.getPassword().equals(user.getPassword())
							&& userData.getUsertype().equals("admin"))
					.count() == 1;
		}
		return verify;
	}
}
