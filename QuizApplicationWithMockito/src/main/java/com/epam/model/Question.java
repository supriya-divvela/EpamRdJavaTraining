package com.epam.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name = "questions")
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int questionid;
	private int qNo;
	private String title;
	@ElementCollection(targetClass = String.class)
	private List<String> options = new ArrayList<>();
	private String difficulty;
	private String taggingTopic;
	@ElementCollection(targetClass = Integer.class)
	private Set<Integer> answers = new TreeSet<>();
	private int marks;
	@ManyToMany(mappedBy = "listOfQuestions")
	private List<Quiz> quizs;

	public Question() {
	}

	public Question(int qNo, String title, List<String> options, String difficulty, String taggingTopic,
			Set<Integer> answers, int marks) {
		this.qNo = qNo;
		this.title = title;
		this.options = options;
		this.difficulty = difficulty;
		this.taggingTopic = taggingTopic;
		this.answers = answers;
		this.marks = marks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answers, difficulty, marks, options, qNo, taggingTopic, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return obj.toString().equals(this.toString());
	}

	@Override
	public String toString() {
		return "Question [qNo=" + qNo + ", title=" + title + ", options=" + options
				+ ", difficulty=" + difficulty + ", taggingTopic=" + taggingTopic + ", answers=" + answers + ", marks="
				+ marks + "]";
	}
	
	

}
