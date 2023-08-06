package com.epam.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class QuestionDto {
	private int qNo;
	private String title;
	private List<String> options = new ArrayList<>();
	private String difficulty;
	private String taggingTopic;
	private Set<Integer> answers = new TreeSet<>();
	private int marks;

	public QuestionDto() {
	}

	public QuestionDto(int qNo, String title, List<String> options, String difficulty, String taggingTopic,
			Set<Integer> answers, int marks) {
		this.qNo = qNo;
		this.title = title;
		this.options = options;
		this.difficulty = difficulty;
		this.taggingTopic = taggingTopic;
		this.answers = answers;
		this.marks = marks;
	}

	public int getQNo() {
		return qNo;
	}

	public void setQNo(int qNo) {
		this.qNo = qNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTaggingTopic() {
		return taggingTopic;
	}

	public void setTaggingTopic(String taggingTopic) {
		this.taggingTopic = taggingTopic;
	}

	public Set<Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Integer> answers) {
		this.answers = answers;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Question [qNo=" + qNo + ", title=" + title + ", options=" + options + ", difficulty=" + difficulty
				+ ", taggingTopic=" + taggingTopic + ", answers=" + answers + ", marks=" + marks + "]";
	}

}
