package com.epam.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.exception.DuplicateUserException;
import com.epam.model.User;
@Repository
public interface UserDao {
	List<User> getUserData();

	String addUser(User user) throws DuplicateUserException;

	boolean findUser(User user);
}
