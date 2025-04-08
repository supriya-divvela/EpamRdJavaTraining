package com.epam.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Table(name = "quizs")
@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizid;
	private int quizNo;
	private String title;
	@ManyToMany
	private List<Question> listOfQuestions = new ArrayList<>();

	public Quiz() {

	}

	public Quiz(int quizNo, String title, List<Question> listOfQuestions) {
		super();
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
	public int hashCode() {
		return Objects.hash(listOfQuestions, quizNo, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		return Objects.equals(listOfQuestions, other.listOfQuestions) && quizNo == other.quizNo
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Quiz [quizNo=" + quizNo + ", title=" + title + ", listOfQuestions=" + listOfQuestions + "]";
	}
}
