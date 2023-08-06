package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	void deleteById(int id);

}
