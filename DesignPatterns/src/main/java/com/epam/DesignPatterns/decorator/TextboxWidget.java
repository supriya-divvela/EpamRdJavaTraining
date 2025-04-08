package com.epam.DesignPatterns.decorator;

public class TextboxWidget extends webpageDecorator {
	public TextboxWidget(Webpage webpage,int rank) {
		super(webpage,rank);
	}
	public int getRank() {
		return super.getRank();
	}
}
