package com.epam.rd;

public class QuizDemo {
	private static int m;
	public static String replaceWithAsterick(String s1) {
		int i = 0;
		String new1 = "";
		while (i < s1.length()) {
			if (Character.isDigit(s1.charAt(i))) {
				int j = i + 1;
				String num = s1.charAt(i) + "";
				while (j < s1.length() && Character.isDigit(s1.charAt(j))) {
					num += s1.charAt(j);
					j++;
				}
				i += num.length();
				for (int k = 0; k < Integer.parseInt(num); k++) {
					new1 += '*';
				}

			} else {
				new1 += s1.charAt(i);
				i++;
			}
		}
		return new1;
	}

	public static boolean function(String s1, String s2) {
		String new1 = "";
		String new2 = "";

		System.out.println(replaceWithAsterick(s1) + " " + replaceWithAsterick(s2));

		if (new1.length() == new2.length()) {
			for (int i = 0; i < new1.length(); i++) {
				if ((new1.charAt(i) == '*' || new2.charAt(i) == '*') || new1.charAt(i) == new2.charAt(i)) {
					continue;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String s1;
		String s2;
		s1 = "a14";
		s2 = "14a";
		s1 = "3i";
		s2 = "K3";
		s1 = "a6b1";
		s2 = "a7b";
		s1 = "a6ba";
		s2 = "a7b";
		System.out.println(function(s1, s2));
	}
}
