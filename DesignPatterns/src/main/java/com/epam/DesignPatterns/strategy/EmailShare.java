package com.epam.DesignPatterns.strategy;

public class EmailShare implements SharingStrategy{

	@Override
	public void share() {
		System.out.println("Photo Shared through email");
	}

}
