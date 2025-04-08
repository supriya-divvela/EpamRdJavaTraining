package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.exception.DuplicateUserException;
import com.epam.model.User;

@Repository
public class UserDaoImplementation implements UserDao {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<User> getUserData() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}

	@Override
	public String addUser(User user) throws DuplicateUserException {
		if (findUser(user)) {
			throw new DuplicateUserException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user.getUsername();
	}

	@Override
	public boolean findUser(User user) {
		return entityManager
				.createQuery("select COUNT(*) from User u where u.username ='" + user.getUsername() + "'", Long.class)
				.getSingleResult() == 1;
	}
}
