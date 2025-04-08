package com.epam.interview;

public class FourThreads implements Runnable {
	private static final Object lock = new Object();
	private static int num = 1; // Shared counter
	private final int limit;
	private final int remainder;

	public FourThreads(int limit, int remainder) {
		this.limit = limit;
		this.remainder = remainder;
	}

	@Override
	public void run() {
		while (num <= limit) {
			synchronized (lock) {
				while (num % 2 != remainder) { // Check if it's this thread's turn
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (num > limit)
					return; // Prevent extra prints

				System.out.println(Thread.currentThread().getName() + ": " + num);
				num++;

				lock.notifyAll(); // Notify all waiting threads
			}
		}
	}

	public static void main(String[] args) {
		int limit = 20; // Change this as needed

		Thread first = new Thread(new FourThreads(limit, 1), "first");
		Thread second = new Thread(new FourThreads(limit, 0), "second");
//		Thread third = new Thread(new FourThreads(limit, 3), "third");
//		Thread fourth = new Thread(new FourThreads(limit, 0), "fourth");
		first.start();
		second.start();
//		third.start();
//		fourth.start();
	}
}
