package com.epam.model;

import java.util.Map;

public class Quiz {
	private int quizNo;
	private String title;
	private Map<Question, Integer> listOfQuestions;
	private int totalMarks;

	public Quiz(int quizNo, String title, Map<Question, Integer> listOfQuestions2, int totalMarks) {
		super();
		this.quizNo = quizNo;
		this.title = title;
		this.listOfQuestions = listOfQuestions2;
		this.totalMarks = totalMarks;
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

	public Map<Question, Integer> getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(Map<Question, Integer> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	@Override
	public String toString() {
		return "Quiz [quizNo=" + quizNo + ", title=" + title + ", listOfQuestions=" + listOfQuestions + ", totalMarks="
				+ totalMarks + "]";
	}

}
