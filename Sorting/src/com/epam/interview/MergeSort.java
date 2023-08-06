package com.epam.interview;

import java.util.Arrays;

public class MergeSort {
	static void sort(int[] arr, int low, int mid, int high) {
		int n1 = mid - low + 1;
		int n2 = high - mid;
		int[] left = new int[n1];
		int[] right = new int[n2];
		for (int i = 0; i < n1; i++) {
			left[i] = arr[low + i];
		}
		for (int i = 0; i < n2; i++) {
			right[i] = arr[mid + i + 1];
		}
		int i = 0;
		int j = 0;
		int k = low;
		while (i < n1 && j < n2) {
			if (left[i] < right[j]) {
				arr[k] = left[i];
				i++;
				k++;
			} else {
				arr[k] = right[j];
				j++;
				k++;
			}
		}
		while (i < n1) {
			arr[k] = left[i];
			k++;
			i++;
		}
		while (j < n2) {
			arr[k] = right[j];
			k++;
			j++;
		}
	}

	static void merge(int[] arr, int low, int high) {
		if (low < high) {
			int mid = low + (high - low) / 2;
			merge(arr, low, mid);
			merge(arr, mid + 1, high);
			sort(arr, low, mid, high);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 6, 5, 4, 3, 2, 1 };
		int n = arr.length;
		merge(arr, 0, n - 1);
		System.out.println(Arrays.toString(arr));
	}
}
