package com.epam;

public class Interview {
	public static void main(String[] args) {
		String s1 = "Supriyaheje";
		String s2 = "priyaj";
//		for(int i=0;i<s1.length()-s2.length()+1;i++) {
//			String temp="";
//			for(int j=i;j<s1.length();j++) {
//				temp=temp+s1.charAt(j);
//			}
//			
//			if(temp.equals(s2)) {
//				System.out.println("equal");
//			}
//		}
		if (s1.length() < s2.length()) {
			String swap = s1;
			s2 = s1;
			s1 = swap;
		}
		int i = 0;
		int j = 0;
		while (i < s1.length() && j<s2.length()) {
			System.out.println(i+" "+j);
			if (s1.charAt(i) == s2.charAt(j)) {
				j++;
			}else {
				j=0;
			}
			i++;
		}
		System.out.println(j == s2.length());
	}
}
