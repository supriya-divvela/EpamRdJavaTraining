package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

public class Hello {
	public static void main(String[] args) {
//		String str = "ab2[c2[b]]e3[a]z";
//		String ans = "";
//		Deque<String> stack = new LinkedList<>();
//		int i = str.length() - 1;
//		while (i >= 0) {
//			if ((str.charAt(i) + "").equals("]")) {
//				stack.push(str.charAt(i) + "");
//
//			} else if ((str.charAt(i) + "").equals("[")) {
//				String cur = "";
//				while (!stack.isEmpty() && !stack.peek().equals("]")) {
//					cur = cur + stack.pop();
//				}
//
//				if (!stack.isEmpty() && stack.peek().equals("]")) {
//					stack.pop();
//					if (i >= 0) {
//						i -= 1;
//						int temp = Integer.parseInt(str.charAt(i) + "");
////						ans = cur + ans;
////						cur=ans;
//						String temp2=cur;
//						for (int j = 0; j < temp-1; j++) {
//							temp2=temp2+cur;
//						}
//						ans=ans+temp2;
//					}
//				}
//				
//			} else {
//				ans=ans+str.charAt(i)+"";
//			}
//			i -= 1;
//			System.out.println(stack);
//		}
//		System.out.println(ans +" "+ ans.equals("abcbbcbbeaaaz"));

//		int[] arr = { 1, 1, 1, 1, 2, 3, 4, 5, 5, 5, 5, 5 };
//		Arrays.sort(arr);
//		int low = 0;
//		int k = 6;
//		List<List<Integer>> ans = new ArrayList<>();
//		int high = arr.length - 1;
//		while (low < high) {
//			if (arr[low] + arr[high] == k) {
//				ans.add(List.of(arr[low], arr[high]));
//				low++;
//				high--;
//				while (low < high && arr[low] == arr[low + 1]) {
//					low++;
//				}
//				while (low < high && arr[high] == arr[high - 1]) {
//					high--;
//				}
//			} else if (arr[low] + arr[high] < k) {
//				low++;
//			} else {
//				high--;
//			}
//		}
//		System.out.println(ans);

		String s = "ab2[c2[b]]e3[a]z";
		while (s.contains('[' + "")) {
			int i = s.lastIndexOf('[');
			int number = Integer.parseInt(s.charAt(i - 1) + "");
			i += 1;
			String temp = "";
			while (s.charAt(i) != ']') {
				temp += s.charAt(i) + "";
				i += 1;
			}
			String ans = "";
			for (int j = 0; j < number; j++) {
				ans = ans + temp;
			}
			s = s.replace(number + "[" + temp + "]", ans);
		}
		System.out.println(s + " " + s.equals("abcbbcbbeaaaz"));
		char[] c = "supr".toCharArray();
		System.out.println(new String(c));

		List<String> list = new LinkedList<String>();

		// Adding elements to above LinkedList
		// using add() method
		list.add("Geeks");
		list.add("for");
		list.add("Geeks");
		list.add("Practice");
		String[] arr = list.toArray(new String[0]);
		System.out.println(Arrays.toString(arr));

		int n = -2;
		boolean temp=false;
		if(n<0)
			temp=true;
		double x = 2;
		n=Math.abs(n);
		double pow = 1;
		while (n != 0) {
			if (n % 2 != 0) {
				pow = pow * x;
				n = n - 1;
			} else {
				x = x * x;
				n = n / 2;
			}
		}
		if(temp)
		System.out.println(1/pow);
		else {
			System.out.println(pow);
		}

	}

}
