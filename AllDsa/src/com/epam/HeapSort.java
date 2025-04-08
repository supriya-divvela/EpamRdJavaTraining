package com.epam;

public class HeapSort {
	public static void sort(int[] arr) {
		int N = arr.length;
		for (int i = N / 2 - 1; i >= 0; i--)
			heapify(arr, N, i);
		for (int i = N - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		}
	}

	public static void heapify(int[] arr, int length, int i) {
		int largest = i; 
		int l = 2 * i + 1; 
		int r = 2 * i + 2;
		if (l < length && arr[l] > arr[largest])
			largest = l;
		if (r < length && arr[r] > arr[largest])
			largest = r;
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			heapify(arr, length, largest);
		}
	}
    public static int[] sortArray(int[] nums) {
        if(nums==null){
            return null;
        }
        if(nums.length==0){
            return new int[0];
        }
        sort(nums);
        return nums;
    }
	static void printArray(int arr[]) {
		int N = arr.length;
		for (int i = 0; i < N; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		int arr[] = { 12, 11, 13, 5, 6, 7 };
		int N = arr.length;

		arr=sortArray(arr);

		System.out.println("Sorted array is");
		printArray(arr);
	}
}
