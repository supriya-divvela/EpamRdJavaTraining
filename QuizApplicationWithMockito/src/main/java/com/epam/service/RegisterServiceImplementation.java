package com.epam.service;

import com.epam.model.User;
import com.epam.repository.UserDao;

public class RegisterServiceImplementation implements RegisterService{
	private UserDao  userDao;
	private LoginServiceImplementation loginServiceImplementation;
	public RegisterServiceImplementation(UserDao  userDao,LoginServiceImplementation loginServiceImplementation) {
		this.userDao=userDao;
		this.loginServiceImplementation=loginServiceImplementation;
	}
	@Override
	public boolean addUser(User user){
		boolean addedUser=false;
		if (!loginServiceImplementation.getAuthentication(user)) {
			addedUser=true;
			userDao.addUser(user);
		} 
		return addedUser;
	}
}
