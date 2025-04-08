package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epam.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	void deleteByUsername(String username);

	Optional<User> findByUsername(String username);
	@Query("Select u.id from User u WHERE u.username=:username")
	int findUserIdByUsername(@Param("username") String username);

}
