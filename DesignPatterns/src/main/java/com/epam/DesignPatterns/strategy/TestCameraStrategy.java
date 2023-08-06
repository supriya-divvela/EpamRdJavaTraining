package com.epam.DesignPatterns.strategy;

public class TestCameraStrategy {
	public static void main(String[] args) {
		CameraAppService cameraAppService=new CameraAppService();
		cameraAppService.setStrategy(new CameraPlusApp());
		cameraAppService.methodsInCameraAppService(new EmailShare());
	}
}
