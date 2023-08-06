package com.epam.DesignPatterns;


public class Decorator implements WebPage{

	private WebPage webPage;
	public Decorator(WebPage webPage)
	{
		this.webPage=webPage;
	}
	@Override
	public void addDisplay(Widget widget) {
		webPage.addDisplay(widget);
	}

}
