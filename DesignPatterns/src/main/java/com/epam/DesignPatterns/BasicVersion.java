package com.epam.DesignPatterns;


public class BasicVersion implements WebPage{

	@Override
	public void addDisplay(Widget widget) {
		//code
		System.out.println(widget.name+" Successfully added");
		
	}

}
