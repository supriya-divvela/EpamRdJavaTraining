package com.epam.DesignPatterns.strategy;

@FunctionalInterface
public interface CameraAppStrategy {
	static void takePhoto() {
		System.out.println("Photo taken");
	}

	void editPhoto();

	static void savePhoto() {
		System.out.println("Photo Saved");
	}

	default void sharePhoto(SharingStrategy service) {
		service.share();
	}
}
