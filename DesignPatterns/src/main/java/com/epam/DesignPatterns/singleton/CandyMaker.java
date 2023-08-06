package com.epam.DesignPatterns.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CandyMaker {
	private boolean empty;
	private boolean boiled;
	private static CandyMaker instance;

	// This code is only started when the boiler is empty
	private CandyMaker() {
		empty = true;
		boiled = false;
	}

	public static CandyMaker getInstance() {
		if (instance == null) {
			synchronized (CandyMaker.class) {
				if (instance == null) {
					instance = new CandyMaker();
				}
			}
		}
		System.out.println(instance);
		return instance;
	}

	// to fill the boiler it must be empty, and once its full,
	// we set the empty and boiled flags
	public void fill() {
		if (isEmpty()) {
			empty = false;
			boiled = false;
			// fill the candyMaker with milk and choclate mix
		}
	}

	// To drain the boiler, it must be full(non-empty) and also boiled.
	// once it is drained, we set the empty back to true
	public void drain() {
		if (!isEmpty() && isBoiled()) {
			// drain the boiled milk and chocolate
			empty = true;
		}
	}

	// to boil the mixture the boiler has to be full and not already boiled.
	// once its boiled we set the boiled flag to true
	public void boil() {
		if (!isEmpty() && !isBoiled()) {
			// bring the content to boil
			boiled = true;
		}
	}

	public boolean isEmpty() {
		return empty;
	}

	public boolean isBoiled() {
		return boiled;
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(CandyMaker::getInstance);
		service.submit(CandyMaker::getInstance);
		service.shutdown();
//		CandyMaker candyMaker1 = CandyMaker.getInstance();
//		System.out.println(candyMaker1);
//		CandyMaker candyMaker2 = CandyMaker.getInstance();
//		System.out.println(candyMaker2);
	}
}
