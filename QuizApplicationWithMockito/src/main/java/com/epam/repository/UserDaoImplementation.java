package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.epam.model.User;

public class UserDaoImplementation implements UserDao {
	private EntityManager entityManager = EntityManagerInstance.getEntityManagerInstance();

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
