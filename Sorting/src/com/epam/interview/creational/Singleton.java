package com.epam.interview.creational;

public class Singleton {
	private static Singleton eagerInstance = new Singleton();
	private static Singleton lazyIntance;
	private static Singleton synchronizedInstance;
	private static Singleton synchronizedBlockInstance;
	
	private Singleton() {

	}

	public static Singleton getEagerInstance() {
		return eagerInstance;
	}

	public static Singleton getLazyInstance() {
		if (lazyIntance == null) {
			lazyIntance = new Singleton();
		}
		return lazyIntance;
	}

	public static synchronized Singleton getSynchronizedInstance() {
		if (synchronizedInstance == null) {
			synchronizedInstance = new Singleton();
		}
		return synchronizedInstance;
	}

	public static Singleton getSynchronizationBlockInstance() {
		synchronized (Singleton.class) {
			if (synchronizedBlockInstance == null) {
				synchronizedBlockInstance = new Singleton();
			}
		}
		return synchronizedBlockInstance;
	}

	public static void main(String[] args) {
		System.out.println(Singleton.getEagerInstance());
		System.out.println(Singleton.getLazyInstance());
		System.out.println(Singleton.getSynchronizedInstance());
		System.out.println(Singleton.getSynchronizationBlockInstance());
	}
}