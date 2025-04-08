package com.epam;

public class Student {
	private String id;
	private String grade;

	public Student(String id, String grade) {
		this.id = id;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public String getGrade() {
		return grade;
	}
	@Override
	public String toString() {
		return this.id+" "+this.grade;
		
	}

}
