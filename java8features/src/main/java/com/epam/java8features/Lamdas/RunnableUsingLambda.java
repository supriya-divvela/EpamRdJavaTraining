package com.epam.java8features.Lamdas;

public class RunnableUsingLambda {
	public static void main(String[] args) {
		Runnable runnable = () -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println("Child Thread: " + i);
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
		for (int i = 1; i <= 10; i++) {
			System.out.println("Main Thread: " + i);
		}
	}
}
