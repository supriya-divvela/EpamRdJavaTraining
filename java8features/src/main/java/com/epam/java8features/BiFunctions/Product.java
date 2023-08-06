package com.epam.java8features.BiFunctions;

public class Product {
	String name;
	int price;

	Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String toString() {
		return this.name + "-" + this.price + "Rs";
	}
}
