package com.epam.DesignPatterns.builder;

public class BicycleBuilderPattern {
	public static void main(String[] args) {
		Bicycle sampleBicycle1=new Bicycle.BicycleBuilder().setCarrier("Carriers").setGears("Gears set").setSeats(4).build();
		System.out.println(sampleBicycle1.getCarrier());
		System.out.println(sampleBicycle1.getGears());
		System.out.println(sampleBicycle1.getSeats());
		System.out.println(sampleBicycle1.getStands());
		Bicycle sampleBicycle2=new Bicycle.BicycleBuilder().setCarrier("Carriers foe 2nd").setGears("Gears set for 2nd").setStands(2).build();
		System.out.println(sampleBicycle2.getCarrier());
		System.out.println(sampleBicycle2.getGears());
		System.out.println(sampleBicycle2.getSeats());
		System.out.println(sampleBicycle2.getStands());
	}
}
