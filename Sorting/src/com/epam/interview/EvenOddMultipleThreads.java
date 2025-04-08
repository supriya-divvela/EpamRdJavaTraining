package com.epam.interview;

public class EvenOddMultipleThreads implements Runnable {
    private static final Object lock = new Object();
    private static int num = 1; // Shared counter
    private final int limit;
    private final int remainder;

    public EvenOddMultipleThreads(int limit, int remainder) {
        this.limit = limit;
        this.remainder = remainder;
    }

    @Override
    public void run() {
        while (num <= limit) {
            synchronized (lock) {
                while (num % 3 != remainder) { // Check if it's this thread's turn
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                if (num > limit) return; // Prevent extra prints

                System.out.println(Thread.currentThread().getName() + ": " + num);
                num++;
                
                lock.notifyAll(); // Notify all waiting threads
            }
        }
    }

    public static void main(String[] args) {
        int limit = 20; // Change this as needed

        Thread oddThread = new Thread(new EvenOddMultipleThreads(limit, 1), "OddThread");
        Thread evenThread = new Thread(new EvenOddMultipleThreads(limit, 2), "EvenThread");
        Thread multipleOfThreeThread = new Thread(new EvenOddMultipleThreads(limit, 0), "MultipleOfThreeThread");

        oddThread.start();
        evenThread.start();
        multipleOfThreeThread.start();
    }
}
