package com.epam.DesignPatterns.decorator;

public class DesktopVersion implements Webpage
{
	private int rank;
	
	public DesktopVersion(int rank) {
		this.rank = rank;
	}

	@Override
	public int getRank() {
		return rank;
	}

}
