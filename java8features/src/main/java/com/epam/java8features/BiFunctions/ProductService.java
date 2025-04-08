package com.epam.java8features.BiFunctions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ProductService {
	public static void getTotalBill(Map<Product, Integer> cartitems) {
		BiFunction<Product, Integer, Double> function = (product, quantity) -> (double) product.getPrice() * quantity;
		double totalcost = cartitems.entrySet().stream()
				.mapToDouble(product -> function.apply(product.getKey(), product.getValue())).sum();
		System.out.println("Total cost is " + totalcost);
	}

	public static Map<Product, Integer> getCart() {
		Map<Product, Integer> cart = new HashMap<>();
		cart.put(createProduct("pen", 30), 5);
		cart.put(createProduct("pencil", 20), 4);
		cart.put(createProduct("book", 10), 3);
		return cart;
	}

	public static Product createProduct(String name, int price) {
		BiFunction<String, Integer, Product> bifunction = (productName, productPrice) -> new Product(productName,
				productPrice);
		return bifunction.apply(name, price);
	}

}
