package com.epam.DesignPatterns.strategy;

public class CameraPlusApp implements CameraAppStrategy {
	@Override
	public void editPhoto() {
		System.out.println("photo edited in camera plus");

	}
}
