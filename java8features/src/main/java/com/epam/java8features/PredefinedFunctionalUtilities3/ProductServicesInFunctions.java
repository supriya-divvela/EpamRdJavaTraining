package com.epam.java8features.PredefinedFunctionalUtilities3;

import java.util.function.Function;

public class ProductServicesInFunctions {
	public static void getCostOfAllProducts() {
		Function<Product, Integer> function = (productsList) -> productsList.getPrice();
		System.out.println("Cost of all products is: "
				+ new ProductsData().getDataSource().stream().mapToInt(product -> function.apply(product)).sum());
	}

	public static void getCostOfAllProductsPriceMoreThan1000() {
		Function<Product, Integer> function = (productsList) -> productsList.getPrice();
		System.out.println("Cost of all products greater than 1000 is : " + new ProductsData().getDataSource().stream()
				.filter(product -> product.getPrice() > 1000).mapToInt(product -> function.apply(product)).sum());
	}

	public static void getCostOfElectronics() {
		Function<Product, Integer> function = (productsList) -> productsList.getPrice();
		System.out.println("Cost of all electronics is: " + new ProductsData().getDataSource().stream()
				.filter(product -> product.getCategory().equalsIgnoreCase("electronics"))
				.mapToInt(product -> function.apply(product)).sum());
	}

	public static void getCostOfElectronicsMoreThan1000() {
		Function<Product, Integer> function = (productsList) -> productsList.getPrice();
		System.out.println("Cost of all products greater than 1000 is: " + new ProductsData().getDataSource().stream()
				.filter(product -> product.getCategory().equalsIgnoreCase("electronics") & product.getPrice() > 1000)
				.mapToInt(product -> function.apply(product)).sum());
	}
}
