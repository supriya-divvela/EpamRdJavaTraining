package com.epam;

public class Test {

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		int[] merged = new int[m + n];
		int i = 0, j = 0, k = 0;
		while (i < m && j < n) {
			if (nums1[i] < nums2[j]) {
				merged[k] = nums1[i];
				i++;
			} else {
				merged[k] = nums2[j];
				j++;
			}
			k++;
		}
		while (i < m) {
			merged[k] = nums1[i];
			i++;
			k++;
		}
		while (j < n) {
			merged[k] = nums2[j];
			j++;
			k++;
		}
		double result = 0;
		if ((m + n) % 2 == 0) {
			result = (double) (merged[(int) (m + n) / 2] + merged[(int) (m + n) / 2 - 1]) / 2;
		} else {
			result = merged[(int) (m + n) / 2];
		}
		return result;
	}

	public static int generateRandom(int seed, int min, int max) {
		// Using a simple linear congruential generator (LCG) formula:
		// newSeed = (a * oldSeed + c) % m
		// where `a`, `c`, and `m` are constants.
		System.out.println(seed);
		int a = 1103515245;
		int c = 12345;
		int m = 32768; // Using a smaller modulus for simplicity
		seed = (a * seed + c) % m;

		// Scale to desired range
		int range = max - min + 1;
		return Math.abs(seed % range) + min;
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 2 }, nums2 = { 3, 4 };
//		System.out.println(findMedianSortedArrays(nums1, nums2));

		int seed = (int) System.currentTimeMillis();
		
		// Generate random numbers in the range of 1 to 100
		for (int i = 0; i < 10; i++) {
			int randomNumber = generateRandom(seed, 1, 100);
			System.out.println("Random Number " + (i + 1) + ": " + randomNumber);

			// Update seed with the generated number for next iteration
			seed = randomNumber;
		}
	}
}
