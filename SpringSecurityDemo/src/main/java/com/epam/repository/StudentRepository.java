package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	Optional<Student> findByUsername(String username);

}
