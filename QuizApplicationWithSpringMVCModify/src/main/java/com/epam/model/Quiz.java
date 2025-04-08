package com.epam.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Table(name = "quizs")
@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizid;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
	@GenericGenerator(name = "question_seq", strategy = "com.epam.model.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "200000"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "Question"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private int hello;
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
