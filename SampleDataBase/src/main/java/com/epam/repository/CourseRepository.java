package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Quiz;
@Repository
public interface CourseRepository extends JpaRepository<Quiz, Integer>{

}
