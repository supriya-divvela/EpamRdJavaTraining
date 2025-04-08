package com.epam.repository;

import java.util.List;

import com.epam.exception.DuplicateUserException;
import com.epam.model.User;

public interface UserDao {
	List<User> getUserData();

	String addUser(User user) throws DuplicateUserException;

	boolean findUser(User user);
}
