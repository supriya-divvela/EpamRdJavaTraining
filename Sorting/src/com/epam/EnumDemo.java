package com.epam;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum EnumDemo{
	 MONDAY,TUESDAY;
	private EnumDemo() {}
//	MONDAY(5),TUESDAY(12),WEDNESDAY(10);
//	private int val;
//	private EnumDemo(int value) {
//		this.setVal(value);
//	}
//	public int getVal() {
//		return val;
//	}
//	public void setVal(int value) {
//		this.val = value;
//	}
	public static void main(String[] args) {
		for(EnumDemo e:EnumDemo.values()) {
			System.out.println(e.ordinal());
		}
//		System.out.println(EnumDemo.valueOf("MONDAY").getVal());
//		System.out.println(EnumDemo.values()+" "+Arrays.toString(EnumDemo.values()));
	}
}

