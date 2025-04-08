package com.epam.jparepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsername(String username);

	boolean existsByUsernameAndPasswordAndRole(String username,String password,String usertype);

	Optional<User> findByUsername(String username);
}
