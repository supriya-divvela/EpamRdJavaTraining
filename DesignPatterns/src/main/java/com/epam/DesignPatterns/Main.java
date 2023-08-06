package com.epam.DesignPatterns;

public class Main {
public static void main(String[] args) {
	WebPage basic=new BasicVersion();
	WebPage basicDesktop=new DesktopVersion(basic);
	WebPage basicDesktopMobile=new MobileVersion(basicDesktop);
	basicDesktopMobile.addDisplay(new Widget("button",10));
	System.out.println();
	basicDesktopMobile.addDisplay(new Widget("textbox",7));
	System.out.println();
	System.out.println("DesktopVersion rank :"+DesktopVersion.rank);
	System.out.println("MobileVersion rank :"+MobileVersion.rank);
	System.out.println();
	WebPage basicMobile=new MobileVersion(basic);
	WebPage basicMobileDesktop=new DesktopVersion(basicMobile);
	basicMobileDesktop.addDisplay(new Widget("checkbox",1));
	System.out.println();
	basicDesktopMobile.addDisplay(new Widget("radiobutton",5));
	System.out.println();
	System.out.println("DesktopVersion rank :"+DesktopVersion.rank);
	System.out.println("MobileVersion rank :"+MobileVersion.rank);
}
}
