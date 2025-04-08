package com.epam.DesignPatterns;


public class MobileVersion extends Decorator{

	static int rank=10;
	public MobileVersion(WebPage webPage) {
		super(webPage);
	}
	
	@Override
	public void addDisplay(Widget widget)
	{
		super.addDisplay(widget);
		addToMobile(widget);
	}

	public void addToMobile(Widget widget) {
		// code to add to MOBILE
		rank=rank+widget.weight;
		System.out.println(widget.name+" added successfully to mobile");
	}
	
}
