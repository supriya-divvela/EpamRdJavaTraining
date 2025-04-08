package com.epam;

public final class FinalClass {
	private final int id;
	private final String name;
	public FinalClass(int id,String name) {
		this.id=id;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public static void main(String[] args) {
		FinalClass fc=new FinalClass(1, "Supriya");
		System.out.println(fc.id+" "+fc.name);
//		fc.name="Pavan";
	}
}
