package com.epam.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
