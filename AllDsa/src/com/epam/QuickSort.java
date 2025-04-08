package com.epam;

import java.util.Arrays;

public class QuickSort {
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	public static int[] sortArray(int[] nums) {
		if (nums == null) {
			return null;
		}
		if (nums.length == 0) {
			return new int[0];
		}
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}
	public static void main(String[] args) {
		int[] arr = { 12, 11, 13, 5, 6, 7 };
		sortArray(arr);
		Arrays.stream(arr).forEach(e->System.out.println(e));
	}
}
