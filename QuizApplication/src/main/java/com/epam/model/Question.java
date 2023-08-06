package com.epam.model;

import java.util.Set;

public class Question {
	private int QNo;
	private String title;
	private Set<String> options;
	private String difficulty;
	private String taggingTopic;
	private Set<Integer> answers;

	public Question(int qNo, String title, Set<String> options, String difficulty, String taggingTopic,
			Set<Integer> answers) {
		this.QNo = qNo;
		this.title = title;
		this.options = options;
		this.difficulty = difficulty;
		this.taggingTopic = taggingTopic;
		this.answers = answers;
	}

	public int getQNo() {
		return QNo;
	}

	public void setQNo(int qNo) {
		QNo = qNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<String> getOptions() {
		return options;
	}

	public void setOptions(Set<String> options) {
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

	@Override
	public String toString() {
		return "Question [QNo=" + QNo + ", title=" + title + ", options=" + options + ", difficulty=" + difficulty
				+ ", taggingTopic=" + taggingTopic + ", answer=" + answers + "]";
	}

}
