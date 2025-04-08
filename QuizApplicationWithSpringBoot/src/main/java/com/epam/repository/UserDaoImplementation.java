package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.model.User;

@Repository
public class UserDaoImplementation implements UserDao {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<User> getUserData() {
		entityManager.getTransaction().begin();
		List<User> users = entityManager.createQuery("from User", User.class).getResultList();
		entityManager.getTransaction().commit();
		return users;
	}

	@Override
	public void addUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();

	}
}
