package com.epam.dto;

import java.util.List;

import com.epam.model.Question;

public class QuizDto {
	private int id;
	private String title;
	private List<Question> listOfQuestions;

	public QuizDto() {
		super();
	}

	public QuizDto(String title, List<Question> listOfQuestions) {
		this.title = title;
		this.listOfQuestions = listOfQuestions;
	}

	public int getId() {
		return id;
	}

	public void setId(int quizNo) {
		this.id = quizNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Question> getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(List<Question> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}
}
