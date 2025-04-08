package com.epam.designpatterns.creational;

class Bank {

}

class HDFC extends Bank {

}

class ICICI extends Bank {

}

class Loan {

}

class HomeLoan extends Loan {

}

class CarLoan extends Loan {

}

abstract class AbstractFactoryBankAndLoan {
	public abstract Bank getBank(String name);

	public abstract Loan getLoan(String name);
}

class BankFactory extends AbstractFactoryBankAndLoan {
	public Bank getBank(String name) {
		if (name == null) {
			return null;
		} else if (name.equalsIgnoreCase("hdfc")) {
			return new HDFC();
		} else if (name.equalsIgnoreCase("icici")) {
			return new ICICI();
		} else {
			return null;
		}
	}

	public Loan getLoan(String name) {
		return null;
	}
}

class LoanFactory extends AbstractFactoryBankAndLoan {
	public Bank getBank(String name) {
		return null;
	}

	public Loan getLoan(String name) {
		if (name == null) {
			return null;
		} else if (name.equalsIgnoreCase("car")) {
			return new HomeLoan();
		} else if (name.equalsIgnoreCase("home")) {
			return new CarLoan();
		} else {
			return null;
		}
	}
}

class FactoryCreator {
	public static AbstractFactoryBankAndLoan getFactory(String choice) {
		if (choice.equalsIgnoreCase("Bank")) {
			return new BankFactory();
		} else if (choice.equalsIgnoreCase("Loan")) {
			return new LoanFactory();
		}
		return null;

	}
}

public class AbstractFactory {
	public static void main(String[] args) {
//		FactoryCreator factory = new FactoryCreator();
		AbstractFactoryBankAndLoan abstractFactoryBankAndLoan = FactoryCreator.getFactory("bank");
		System.out.println(abstractFactoryBankAndLoan.getBank("icici"));
	}
}
