package com.epam.service;

import com.epam.exception.DuplicateUserException;
import com.epam.model.User;

public interface RegisterService {
	String addUser(User user) throws DuplicateUserException;
}
