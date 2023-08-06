package com.epam.DesignPatterns.decorator;

public class TestDecorator {
	public static void main(String[] args) {
		Webpage version1=new ButtonWidget(new TextboxWidget(new MobileVersion(10),20),30);
		Webpage version2=new TextboxWidget(new ButtonWidget(new DesktopVersion(20),30),40);
		Webpage version3=new CheckboxWideget(new TextboxWidget(new ButtonWidget(new MobileVersion(1),2),3),4);
		System.out.println(version1.getRank());
		System.out.println(version2.getRank());
		System.out.println(version3.getRank());
	}
}
