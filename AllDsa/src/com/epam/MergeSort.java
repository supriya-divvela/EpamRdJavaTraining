package com.epam;


public class MergeSort {
	 static void merge(int[] arr, int l, int m, int r)
	{
		int n1 = m - l + 1;
		int n2 = r - m;
		int[] left = new int[n1];
		int[] right = new int[n2];
		for (int i = 0; i < n1; ++i) {
			left[i] = arr[l + i];
		}
		for (int j = 0; j < n2; ++j) {
			right[j] = arr[m + 1 + j];
		}
		int i = 0;
        int j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (left[i] <= right[j]) {
				arr[k] = left[i];
				i++;
			}
			else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = left[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = right[j];
			j++;
			k++;
		}
	}
	 static void mergeSort(int[] arr, int l, int r)
	{
		if (l < r) {
			int m = l + (r - l) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}
    public static int[] sortArray(int[] nums) {
        if(nums==null || nums.length==0){
            return new int[0];
        }
        mergeSort(nums,0,nums.length-1);
        return nums;
    }

	public static void main(String[] args)
	{
		int[] arr = { 12, 11, 13, 5, 6, 7 };
		sortArray(arr);
	}
}
