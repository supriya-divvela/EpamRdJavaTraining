package com.epam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.repository.UserRepository;
import com.epam.service.interfaces.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public String getRole(String username) {
		return userRepository.findByUsername(username).get().getRole();
	}

}
