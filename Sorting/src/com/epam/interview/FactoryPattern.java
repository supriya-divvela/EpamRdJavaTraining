package com.epam.interview;
class Electronis{
	
}
class Ac extends Electronis{
	
}
class Fridge extends Electronis{
	
}
class Grinder extends Electronis{
	
}
class Factory{
	public Electronis getElectronis(String name) {
		if(name==null) {
			return null;
		}else if(name.equalsIgnoreCase("frideg")) {
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
		Factory factory=new Factory();
		Electronis electronis=factory.getElectronis("ac");
		System.out.println(electronis);
	}
}
