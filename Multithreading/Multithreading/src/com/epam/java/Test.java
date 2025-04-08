package com.epam.java;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Instant startTime = Instant.now();
		ATM atm = new ATM();
		atm.deductAmount();

		// Without Multithreading
//		NotificationAPI sms = new SMSNotification();
//		NotificationAPI email = new EmailNotification();
//		NotificationAPI whatsApp = new WhatsAppNotification();
//		NotificationAPI mobileApp = new MobileAppNotification();
//
//		sms.sendNotification();
//		email.sendNotification();
//		whatsApp.sendNotification();
//		mobileApp.sendNotification();
		
		// With Multithreading
//		Thread t1 = new Thread(new SMSNotification());
//		Thread t2 = new Thread(new EmailNotification());
//		Thread t3 = new Thread(new WhatsAppNotification());
//		Thread t4 = new Thread(new MobileAppNotification());
//		
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		
//		t1.join();
//		t2.join();
//		t3.join();
//		t4.join();
		
		// With Executor Framework
		
		//Use fixed thread pool if you know the size
		ExecutorService service = Executors.newFixedThreadPool(4);
		service.execute(new SMSNotification());
		service.execute(new EmailNotification());
		service.execute(new WhatsAppNotification());
		service.execute(new MobileAppNotification());
		service.shutdown();
		
		service.awaitTermination(1100L, TimeUnit.MILLISECONDS);
		

		System.out.println("Total time taken to complete the process : "
				+ Duration.between(startTime, Instant.now()).toMillis() + " milliseconds");
		
		//Use cached thread pool if you don't know the size
		/*
		 * ExecutorService service = Executors.newCachedThreadPool();
		 * List<NotificationAPI> listOfTasks = Arrays.asList(new SMSNotification(),new
		 * EmailNotification(), new WhatsAppNotification(), new
		 * MobileAppNotification()); for(NotificationAPI api : listOfTasks) {
		 * service.execute(api); }
		 */
	}
}
