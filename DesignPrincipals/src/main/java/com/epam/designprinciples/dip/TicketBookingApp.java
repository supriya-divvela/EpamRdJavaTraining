package com.epam.designprinciples.dip;

public class TicketBookingApp {

	private BankCard bankCard;

	public TicketBookingApp(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	public void doPayment(int noOfTickets, int amount) {
		bankCard.doTransaction(amount);
	}

	public static void main(String[] args) {
		BankCard debitCard = new DebitCard();
		TicketBookingApp ticketApp = new TicketBookingApp(debitCard);
		ticketApp.doPayment(4, 5000);
	}
}
