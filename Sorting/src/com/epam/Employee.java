package com.epam;

public class Employee{
	private Integer id;
	private String branch;
	private double salary;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setName(String name) {
		this.branch = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee(Integer id, String name, double salary) {
		super();
		this.id = id;
		this.branch = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + branch + ", salary=" + salary + "]";
	}

}
