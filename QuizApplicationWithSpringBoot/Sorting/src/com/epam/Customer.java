package com.epam;

public class Customer implements Comparable<Customer>{
	Integer id;
	String name;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int id, String name) {
		super();
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
	public int compareTo(Customer obj) {
		return this.name.compareTo(obj.name);
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	
	
}

