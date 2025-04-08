package com.epam.java8features.BinaryAndUnaryOperators;

import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;

public class BinaryAndUnaryOperator {
	public static void isPrimeNumber() {
		IntPredicate predicate = n -> {
			boolean isprime = true;
			if (n < 2) {
				isprime = false;
			} else {
				for (int i = 2; i < (int) n / 2 + 1; i++) {
					if (n % i == 0) {
						isprime = false;
						break;
					}
				}
			}
			return isprime;
		};
		try (Scanner sc = new Scanner(System.in)) {
			int number = sc.nextInt();
			if (predicate.test(number)) {
				System.out.println(number + " is a prime number.");
			} else {
				System.out.println(number + " is not a prime number.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void getRandomNumberBelow5000() {
		IntSupplier supplier = () -> (int) (Math.random() * (5000));
		System.out.println("Random number below 5000 is " + supplier.getAsInt());
	}

	public static void findSquareOfANumber() {
		IntConsumer consumer = n -> System.out.println("Square of " + n + " is " + n * n);
		try (Scanner sc = new Scanner(System.in)) {
			int number = sc.nextInt();
			consumer.accept(number);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		BinaryAndUnaryOperator.isPrimeNumber();
		BinaryAndUnaryOperator.getRandomNumberBelow5000();
		BinaryAndUnaryOperator.findSquareOfANumber();
	}
}
