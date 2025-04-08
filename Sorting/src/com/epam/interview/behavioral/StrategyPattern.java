package com.epam.interview.behavioral;

interface Calculation {
	public int calculate();
}

class Add implements Calculation {

	@Override
	public int calculate() {
		// TODO Auto-generated method stub
		return 1;
	}

}

class Sub implements Calculation {

	@Override
	public int calculate() {
		// TODO Auto-generated method stub
		return 2;
	}

}

class Mul implements Calculation {

	@Override
	public int calculate() {
		// TODO Auto-generated method stub
		return 2;
	}

}

class Strategey implements Calculation {
	private Calculation calculation;

	public Strategey(Calculation calculation) {
		this.calculation = calculation;
	}

	@Override
	public int calculate() {
		return calculation.calculate();
	}
}

public class StrategyPattern {
	public static void main(String[] args) {
		Strategey s=new Strategey(new Sub());
		System.err.println(s.calculate());
	}
}
