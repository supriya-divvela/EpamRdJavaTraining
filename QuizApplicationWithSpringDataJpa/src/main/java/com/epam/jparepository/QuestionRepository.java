package com.epam.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.epam.model.Question;
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	void deleteByQNo(int qNo);

	boolean existsByQNo(int qNo);

	Question findByQNo(int qNo);

	boolean existByQNo(int qNo);

}