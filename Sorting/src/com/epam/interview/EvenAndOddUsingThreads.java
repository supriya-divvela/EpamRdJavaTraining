package com.epam.interview;

public class EvenAndOddUsingThreads implements Runnable {
	private static final Object lock = new Object();
	private int limit;
	private int i = 1;
	public EvenAndOddUsingThreads(int limit) {
		this.limit=limit;
	}
	@Override
	public void run() {
		while (i < limit) {
			while (i % 2 == 0 && Thread.currentThread().getName().equals("even")) {
				synchronized (lock) {
					System.out.println("even" + " " + i);
					i++;
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			while (i % 2 != 0 && Thread.currentThread().getName().equals("odd")) {
				synchronized (lock) {
					System.out.println("odd" + " " + i);
					i++;
					lock.notify();
				}
			}
		}
	}

	public static void main(String[] args) {
		EvenAndOddUsingThreads r = new EvenAndOddUsingThreads(10);
		Thread t1 = new Thread(r, "even");
		Thread t2 = new Thread(r, "odd");
		t1.start();
		t2.start();
	}

}
