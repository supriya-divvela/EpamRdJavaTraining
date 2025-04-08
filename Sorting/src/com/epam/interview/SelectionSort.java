package com.epam.interview;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 6, 5, 4, 3, 2, 1 };
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int minIndex=i;
			for (int j = i+1; j < n ; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex=j;
				}
			}
			int temp=arr[minIndex];
			arr[minIndex]=arr[i];
			arr[i]=temp;
		}
		System.out.println(Arrays.toString(arr));
	}
}
