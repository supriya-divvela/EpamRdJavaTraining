package com.epam.designprinciples.dip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebitCard implements BankCard {
	private static final Logger logger = LogManager.getLogger("DebitCard.class");

	@Override
	public void doTransaction(int amount) {
		logger.info("tx done with DebitCard");
	}
}