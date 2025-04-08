package com.epam.service;

import com.epam.model.User;

public interface LoginService {
	boolean getAuthentication(User user);

	boolean validate(User user);
}
