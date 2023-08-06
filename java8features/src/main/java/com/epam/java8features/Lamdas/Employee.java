package com.epam.java8features.Lamdas;

public class Employee {
	int id;
	String name;

	Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Id : " + this.id + " " + "Name is : " + this.name;
	}
}