package com.epam.shallowanddeepcopy;

public class Employee implements Cloneable {
	String name;
	int age;
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Employee(Employee employee) {
		this.name=employee.name;
		this.age=employee.age;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();

	}
}
