package com.epam.DesignPatterns.decorator;

public class MobileVersion implements Webpage {

	private int rank;

	public MobileVersion(int rank) {
		this.rank = rank;
	}

	@Override
	public int getRank() {
		return rank;
	}
}
