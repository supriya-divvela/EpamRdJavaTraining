package com.epam.jparepository;

import org.springframework.data.repository.ListCrudRepository;

import com.epam.model.Quiz;

public interface QuizRepository extends ListCrudRepository<Quiz,Long>{

	boolean existsByQuizNo(int quizNo);

	void deleteByQuizNo(int quizNo);

	Quiz findByQuizNo(int quizNo);

}
