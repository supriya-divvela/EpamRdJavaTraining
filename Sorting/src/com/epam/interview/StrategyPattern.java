package com.epam.interview;

interface Calculation {
	public int calculate();
}

class Add implements Calculation {

	@Override
	public int calculate() {
		// TODO Auto-generated method stub
		return 0;
	}

}

class Sub implements Calculation {

	@Override
	public int calculate() {
		// TODO Auto-generated method stub
		return 0;
	}

}

class Mul implements Calculation {

	@Override
	public int calculate() {
		// TODO Auto-generated method stub
		return 0;
	}

}

class Strategey implements Calculation {
	private Calculation calculation;

	public Strategey(Calculation calculation) {
		this.calculation = calculation;
	}

	public int getCalculate() {
		return calculation.calculate();
	}

	@Override
	public int calculate() {
		return calculation.calculate();
	}
}

public class StrategyPattern {
	public static void main(String[] args) {
		Strategey s=new Strategey(new Add());
		System.err.println(s.getCalculate());
	}
}
