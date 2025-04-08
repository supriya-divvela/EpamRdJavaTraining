package com.epam.interview.creational;
class Electronis{
	
}
class Ac extends Electronis{
	
}
class Fridge extends Electronis{
	
}
class Grinder extends Electronis{
	
}
class Factory{
	public static Electronis getElectronis(String name) {
		if(name==null) {
			return null;
		}else if(name.equalsIgnoreCase("fridge")) {
			return new Fridge();
		}else if(name.equalsIgnoreCase("ac")) {
			return new Ac();
		}else if(name.equalsIgnoreCase("grinder")) {
			return new Grinder();
		}else {
			return null;
		}
	}
}
public class FactoryPattern {
	public static void main(String[] args) {
//		String s="sup";
//		System.out.println(s.equals(null));
//		System.out.println(s==null);
//		Factory factory=new Factory();
		Electronis electronis=Factory.getElectronis("ac");
		System.out.println(electronis);
	}
}
