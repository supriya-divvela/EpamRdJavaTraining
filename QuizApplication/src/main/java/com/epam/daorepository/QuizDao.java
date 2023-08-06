package com.epam.daorepository;

import java.util.List;

import com.epam.datalayer.DataSource;
import com.epam.model.Quiz;

public class QuizDao {
	private DataSource dataSource = new DataSource();

	public List<Quiz> getQuizsList() {
		return dataSource.getQuizsList();
	}
}
