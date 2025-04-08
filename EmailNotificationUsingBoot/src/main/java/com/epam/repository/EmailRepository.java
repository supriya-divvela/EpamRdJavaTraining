package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Email;
@Repository
public interface EmailRepository extends JpaRepository<Email, Integer>{

}
