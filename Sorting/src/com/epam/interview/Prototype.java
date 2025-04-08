package com.epam.interview;

interface PrototypePattern {
	public Prototype getClone();
}

public class Prototype implements PrototypePattern {
	public Prototype() {
	}

	@Override
	public Prototype getClone() {
		return new Prototype();
	}
	public static void main(String[] args) {
		System.out.println(new Prototype().getClone());
	}
}
