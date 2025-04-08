package com.epam.interview;

import java.util.Arrays;

public class QuickSort {
	static int partition(int[] arr, int low, int high) {
		int j = high;
		int i = low;
		int pivot = arr[low];
		while (i <j) {
			while (arr[i] <= pivot && i < high){
				i++;
			} 
			while (arr[j] > pivot && j > low){
				j--;
			} 
			if (i < j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[j];
		arr[j] = arr[low];
		arr[low] = temp;
		return j;
	}

	static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int j = partition(arr, low, high);
			quickSort(arr, low, j - 1);
			quickSort(arr, j + 1, high);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 6, 5, 4, 3, 2, 1 };
		int n = arr.length;
		quickSort(arr, 0, n - 1);
		System.out.println(Arrays.toString(arr));
	}
}
