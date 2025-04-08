package com.epam.testdao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.model.User;
import com.epam.repository.UserDaoImplementation;

@ExtendWith(MockitoExtension.class)
class TestUserDao {
	@Mock
	private EntityManager entityManager;
	@InjectMocks
	private UserDaoImplementation userDao;
	@Mock
	private TypedQuery<User> query;
	@Mock
	private EntityTransaction transaction;
	private List<User> usersList;

	@BeforeEach
	void createUsers() {
		usersList = new ArrayList<>(
				Arrays.asList(new User("virat", "1234", "user"), new User("klrahul", "1234", "admin")));
	}

	@Test
	void testUsersData() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		when(entityManager.createQuery("from User", User.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(usersList);
		assertTrue(userDao.getUserData().size() > 0);
	}

	@Test
	void testAddUser() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		User user=new User();
		doNothing().when(entityManager).persist(user);
		userDao.addUser(user);
		verify(transaction).begin();
		verify(transaction,times(1)).commit();
	}

	@Test
	void testAddingNullUser() {
		assertThrows(NullPointerException.class, () -> userDao.addUser(null));
	}
}
