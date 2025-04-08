package com.epam.java;

public class WhatsAppNotification extends NotificationAPI {
	@Override
	public void sendNotification() {
		System.out.println("WhatsApp Notification process is in progress to trigger.....");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("""
			WHATSAPP:
			Your ICICI Bank Acct XX513 debited for Rs 6800.00 on 26-Apr-23.
			UPI: 347451162746. Call 18002662 for dispute. SMS BLOCK 513 to 9215676766
			""");
	}
	
	@Override
	public void run() {
		sendNotification();
	}
}
