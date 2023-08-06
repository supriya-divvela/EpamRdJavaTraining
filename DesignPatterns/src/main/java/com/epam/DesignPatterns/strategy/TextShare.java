package com.epam.DesignPatterns.strategy;

public class TextShare implements SharingStrategy{
	@Override
	public void share() {
		System.out.println("Photo Shared through text");
	}
}
