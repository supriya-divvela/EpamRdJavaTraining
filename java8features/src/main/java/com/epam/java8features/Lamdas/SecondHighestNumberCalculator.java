package com.epam.java8features.Lamdas;

import java.util.Scanner;

interface Finder {
	public void findSecondBiggestNumber(int[] num);
}

public class SecondHighestNumberCalculator {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			int[] numbers = new int[n];
			for (int i = 0; i < n; i++) {
				numbers[i] = sc.nextInt();
			}
			Finder getSecondHighestNumber = (numberslist) -> {
				int first = numberslist[0];
				int second = Integer.MIN_VALUE;
				for (int i = 1; i < n; i++) {
					if (numberslist[i] > first) {
						second = first;
						first = numberslist[i];
					} else if (numberslist[i] > second && second != Integer.MIN_VALUE) {
						second = numberslist[i];
					}
				}
				if (second != Integer.MIN_VALUE) {
					System.out.println("Second Biggest is :" + second);
				} else {
					System.out.println("Only one number");
				}
			};
			getSecondHighestNumber.findSecondBiggestNumber(numbers);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
