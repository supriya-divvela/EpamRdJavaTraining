package com.epam.jparepository;

import org.springframework.data.repository.ListCrudRepository;

import com.epam.model.User;
public interface UserRepository extends ListCrudRepository<User,Long>{

}
