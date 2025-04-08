package com.epam.service;

import com.epam.daorepository.UserDao;
import com.epam.model.User;

public class RegisterService {
	private UserDao userDao = new UserDao();
	public void addUser(User user) {
		userDao.getUserData().add(user);
	}
}
