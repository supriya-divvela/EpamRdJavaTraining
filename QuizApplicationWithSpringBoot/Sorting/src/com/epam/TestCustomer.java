package com.epam;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestCustomer {
	public static void main(String[] args) {
		Instant s = Instant.now();
		List<Integer> num = new ArrayList<>();
		for (int i = 0; i < 10000000; i++) {
			num.add(i);
		}
		System.out.println(Duration.between(s, Instant.now()).toMillis());
		Instant s1 = Instant.now();
		List<Integer> num1 = new LinkedList<>();
		for (int i = 0; i < 10000000; i++) {
			num.add(i);
		}
		System.out.println(Duration.between(s1, Instant.now()).toMillis());
	}
}
