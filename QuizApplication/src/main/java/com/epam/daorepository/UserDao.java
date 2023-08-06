package com.epam.daorepository;

import java.util.List;

import com.epam.datalayer.DataSource;
import com.epam.model.User;

public class UserDao {
	private DataSource dataSource = new DataSource();

	public List<User> getUserData() {
		return dataSource.getUsersData();
	}
}
