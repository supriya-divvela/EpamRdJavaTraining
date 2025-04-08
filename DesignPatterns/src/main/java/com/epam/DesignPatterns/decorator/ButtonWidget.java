package com.epam.DesignPatterns.decorator;

public class ButtonWidget extends webpageDecorator{

	public ButtonWidget(Webpage webpage,int rank) {
		super(webpage,rank);
	}
	public int getRank() {
		return super.getRank();
	}
}
