package com.epam.java8features.Lamdas;

import java.util.Scanner;

@FunctionalInterface
interface PalindromeOrNot {
	public boolean checkPalindrome();
}

public class Palindrome {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			String word = scanner.nextLine();
			PalindromeOrNot string = () -> {
				int n = word.length();
				for (int i = 0; i < n / 2; i++) {
					if (word.charAt(i) != word.charAt(n - i - 1)) {
						return false;
					}
				}
				return true;
			};
			if (string.checkPalindrome()) {
				System.out.println(word + " is a palindrome");
			} else {
				System.out.println(word + " is not a palindrome");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
