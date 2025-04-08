package com.epam;

interface A{
	 static void m1() {
		System.err.println("a");
	}
}
interface B{
	 static void m1() {
		 System.out.println("b");
	 }
	
}
//class C implements A,B{
//
//	@Override
//	public void m1() {
//		// TODO Auto-generated method stub
//		A.super.m1();
//	}
//	
//}
public class TestCustomer implements A,B{
	public static void main(String[] args) {
		A.m1();
	}
//	public static void main(String[] args) {
//		Instant s = Instant.now();
//		List<Integer> num = new ArrayList<>();
//		for (int i = 0; i < 10000000; i++) {
//			num.add(i);
//		}
//		System.out.println(Duration.between(s, Instant.now()).toMillis());
//		Instant s1 = Instant.now();
////		List<Integer> num1 = new LinkedList<>();
//		for (int i = 0; i < 10000000; i++) {
//			num.add(i);
//		}
//		System.out.println(Duration.between(s1, Instant.now()).toMillis());
//	}

}
