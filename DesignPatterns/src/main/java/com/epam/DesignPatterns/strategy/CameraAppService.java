package com.epam.DesignPatterns.strategy;

public class CameraAppService {
	private CameraAppStrategy strategy;

	public void setStrategy(CameraAppStrategy strategy) {
		this.strategy = strategy;
	}

	public void methodsInCameraAppService(SharingStrategy shareStrategy) {
		strategy.editPhoto();
		CameraAppStrategy.savePhoto();
		strategy.sharePhoto(shareStrategy);
		CameraAppStrategy.takePhoto();
	}
}
