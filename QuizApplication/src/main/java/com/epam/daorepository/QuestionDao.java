package com.epam.daorepository;

import java.util.List;

import com.epam.datalayer.DataSource;
import com.epam.model.Question;

public class QuestionDao {
	private DataSource dataSource = new DataSource();

	public List<Question> getQuestionsList() {
		return dataSource.getQuestionsList();
	}
}
