package com.epam.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Input {
	private static Scanner scanner = new Scanner(System.in);
	private static Logger logger = (Logger) LogManager.getLogger("Input.class");

	private Input() {
	}

	public static int readInt() {
		int value = -1;
		 do{
			try {
				value =Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				logger.info("Please Enter numbers only...");
			}
		}while(value<=0);
		return value;
	}

	public static String read() {
		return scanner.nextLine();
	}
}
