package com.epam.designprinciples.lsp;

interface Mobile {
	void sendSMS();

	void call();

}




class A{
	static void m1() {
		System.err.println("a");
	}
}
class B extends A{
	static void m1() {
		System.out.println("b");
	}
	public static void main(String[] args) {
		B b=new B();
		b.m1();
	}
}