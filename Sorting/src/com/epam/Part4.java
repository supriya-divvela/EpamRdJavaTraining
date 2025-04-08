package com.epam;

import java.util.Arrays;

public class Part4 {
	public static int[] sortArray(int[] nums) {
		if (nums == null) {
			return null;
		}
		if (nums.length == 0) {
			return new int[0];
		}
		for (int i = 0; i < nums.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[minIndex]) {
					minIndex = j;
				}
			}
			int temp = nums[minIndex];
			nums[minIndex] = nums[i];
			nums[i] = temp;
		}
		return nums;
	}

	public static void mergeSort(int[] arr, int low, int high) {
//		if(low<high) {
//			int mid = low + (high-low) / 2;
//			mergeSort(arr, low, mid);
//			mergeSort(arr, mid+1, high);
//			merge(arr,low,mid,high);
//		}
		if (low < high) {
			int mid = low + (high - low) / 2;
			mergeSort(arr, low, mid);
			mergeSort(arr, mid + 1, high);
			merge(arr, low, mid, high);
		}
	}

	public static void merge(int[] arr, int low, int mid, int high) {
		int l = mid - low + 1;
		int h = high - mid;
		int[] left = new int[l];
		int[] right = new int[h];
		for (int i = 0; i < l; ++i) {
			left[i] = arr[low + i];
		}
		for (int i = 0; i < h; ++i) {
			right[i] = arr[mid + i + 1];
		}
		int i = 0;
		int j = 0;
		int k = low;
		while (i < l && j < h) {
			if (left[i] <= right[j]) {
				arr[k] = left[i];
				i++;
			} else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		Arrays.stream(arr).forEach(e -> System.out.println(e));
//		System.out.println(k+" "+l+" "+i);
		while (i < l) {
			arr[k] = left[i];
			k++;
			i++;
		}
		while (j < h) {
			arr[k] = right[j];
			k++;
			j++;
		}

	}

	public static boolean checkChar(char ch) {
		return (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122);
	}

	public static void main(String[] args) {
		StringBuilder str = new StringBuilder("A%JK*&UQ");
		int n = str.length();
		int p1 = 0, i = 0;
		int p2 = n - 1;
		char ch;
		while (i < p2) {
			ch = str.charAt(i);
			if (checkChar(ch)) {
				while (!checkChar(str.charAt(p2))) {
					p2--;
				}
				char t;
				t = str.charAt(p2);
				str.replace(p2, p2 + 1, str.charAt(i) + "");
				str.replace(i, i + 1, t + "");
				p2--;
			}
			i++;
		}
		System.out.println(str.toString());
	}

//	public static void main(String[] args) {
//		int[] a = { 5, 2, 3, 1 };
//		mergeSort(a, 0, 3);
//		Arrays.stream(a).forEach(e -> System.out.println(e));
//		String s = "L%Av@$any%a";
//		System.out.println(
//				s.replaceAll("[^a-zA-Z]", "").chars().mapToObj(c -> (char) c + "").reduce("", (s1, s2) -> s2 + s1));
//		String s1 = "[{()}({})}]";
//		while (s1.contains("()") || s1.contains("{}") || s1.contains("[]")) {
//			s1 = s1.replace("()", "");
//			s1 = s1.replace("[]", "");
//			s1 = s1.replace("{}", "");
//		}
//		if (s1.length() == 0) {
//			System.out.println("balanced");
//		} else {
//			System.out.println("Not Balanced");
//		}
//	}
}
