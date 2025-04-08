package com.epam.service;

import com.epam.daorepository.UserDao;
import com.epam.model.User;

public class AdminService {
	private UserDao admindao = new UserDao();

	public void addAdmin(User admin) {
		admindao.getUserData().add(admin);
	}

	public boolean findAdmin(User admin) {
		return admindao.getUserData().stream().filter(admins -> admins.getUsername().equals(admin.getUsername()))
				.count() == 1;
	}
}