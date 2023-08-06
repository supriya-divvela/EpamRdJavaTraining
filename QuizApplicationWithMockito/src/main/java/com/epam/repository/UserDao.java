package com.epam.repository;

import java.util.List;

import com.epam.model.User;

public interface UserDao {
	List<User> getUserData();
	void addUser(User user);
}
