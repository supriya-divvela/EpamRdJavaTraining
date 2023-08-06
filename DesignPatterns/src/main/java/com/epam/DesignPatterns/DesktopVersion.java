package com.epam.DesignPatterns;


public class DesktopVersion extends Decorator{

	static int rank=20;
	public DesktopVersion(WebPage webPage) {
		super(webPage);
	}
	
	@Override
	public void addDisplay(Widget widget)
	{
		super.addDisplay(widget);
		addToDesktop(widget);
	}

	public void addToDesktop(Widget widget) {
		// code to add to DESKTOP
		rank=rank+widget.weight;
		System.out.println(widget.name+" added successfully to desktop");
		
	}

}
