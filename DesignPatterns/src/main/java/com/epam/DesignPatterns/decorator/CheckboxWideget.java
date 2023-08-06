package com.epam.DesignPatterns.decorator;

public class CheckboxWideget extends webpageDecorator {
	public CheckboxWideget(Webpage webpage,int rank) {
		super(webpage,rank);
	}
	public int getRank() {
		return super.getRank();
	}
}
