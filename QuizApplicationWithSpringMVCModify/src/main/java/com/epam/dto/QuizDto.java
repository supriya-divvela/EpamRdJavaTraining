package com.epam.dto;

import java.util.List;

import com.epam.model.Question;

public class QuizDto {
	private int quizNo;
	private String title;
	private List<Question> listOfQuestions;

	public QuizDto() {
		super();
	}

	public QuizDto(int quizNo, String title, List<Question> listOfQuestions) {
		this.quizNo = quizNo;
		this.title = title;
		this.listOfQuestions = listOfQuestions;
	}

	public int getQuizNo() {
		return quizNo;
	}

	public void setQuizNo(int quizNo) {
		this.quizNo = quizNo;
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

	@Override
	public String toString() {
		return "Quiz [quizNo=" + quizNo + ", title=" + title + ", listOfQuestions=" + listOfQuestions + "]";
	}
}
