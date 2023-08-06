package com.epam;

public enum EnumDemo {
	MONDAY(5),TUESDAY(12),WEDNESDAY(10);
	private int value;
	private EnumDemo(int value) {
		this.setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}

