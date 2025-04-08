package com.epam.interview.sortingtechniques;

import java.util.Arrays;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 6, 5, 4, 3, 2, 1 };
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int temp = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > temp) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
		System.out.println(Arrays.toString(arr));
	}
}