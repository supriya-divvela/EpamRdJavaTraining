package com.epam.designprinciples.lsp;

public class Ipod implements MusicService {
	
	@Override
	public void playMusic(String fileName) {
		System.out.println("Playing music " + fileName);

	}
}
