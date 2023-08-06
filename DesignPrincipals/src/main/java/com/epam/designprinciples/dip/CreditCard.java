package com.epam.designprinciples.dip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditCard implements BankCard {
	private static final Logger logger = LogManager.getLogger("CreditCard.class");

	@Override
	public void doTransaction(int amount) {
		logger.info("tx done with CreditCard");
	}
}