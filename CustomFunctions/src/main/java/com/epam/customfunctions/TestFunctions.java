package com.epam.customfunctions;

public class TestFunctions {
	public static void main(String[] args) {
		
		TriFunction<Integer,Integer,Integer,Integer> findMaxOfThreeNumbers=(a,b,c)->(a>b)?(a>c?a:c):(b>c?b:c);
		System.out.println("Maximum of three numbers is "+findMaxOfThreeNumbers.apply(1,20,-9));
		
		TriConsumer<Integer,Integer,Integer> findSquareOfEachNumber=(a,b,c)->{
			System.out.println("Square of first number is "+a*2);
			System.out.println("Square of second number is "+b*2);
			System.out.println("Square of third number is "+c*2);
		};
		findSquareOfEachNumber.accept(10,20,30);
	}
}
