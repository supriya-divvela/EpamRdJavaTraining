package com.epam.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.model.User;
@Service
public interface UserDao {
	List<User> getUserData();

	void addUser(UserDto userDto);
}
