package com.epam.java8features.Lamdas;

import java.util.*;

@FunctionalInterface
interface RotationCheck {
	public boolean rotate(String str1, String str2);
}

public class StringRotation {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			String str1 = scanner.nextLine();
			String str2 = scanner.nextLine();
			if (str1.length() != str2.length()) {
				System.out.println("Both string lengths are not equal...");
			} else {
				RotationCheck rc = (s1, s2) -> {
					s1 = s1.concat(s1);
					return s1.contains(s2);
				};
				if (rc.rotate(str1, str2)) {
					System.out.println("Yes strings are rotations of each other");
				} else {
					System.out.println("No strings are not rotation to each other");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
