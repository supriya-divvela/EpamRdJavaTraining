package com.epam.java8features.Lamdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortingNumbers {
	
	public static void sortNumbersInDescendingOrder() {
		List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Collections.sort(numbersList, Collections.reverseOrder());
		System.out.println("Elements sorted in descending order is " + numbersList);
	}

	public static void sortTreesetNumbersInDescendOrder() {
		List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Set<Integer> numbers = new TreeSet<Integer>((I1, I2) -> (I1 > I2) ? -1 : (I1 < I2) ? 1 : 0);
		numbers.addAll(listOfNumbers);
		System.out.println("Tree set Elements sorted in descending order is " + numbers);
	}
	public static void main(String[] args) {
		SortingNumbers.sortNumbersInDescendingOrder();
		SortingNumbers.sortTreesetNumbersInDescendOrder();
	}
}
