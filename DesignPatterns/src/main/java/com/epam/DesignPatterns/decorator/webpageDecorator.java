package com.epam.DesignPatterns.decorator;

public class webpageDecorator implements Webpage {
	private Webpage webpage;
	private int rank;

	webpageDecorator(Webpage webpage,int rank) {
		this.webpage = webpage;
		this.rank=rank;
	}

	@Override
	public int getRank() {
		return rank + webpage.getRank();
	}
}
