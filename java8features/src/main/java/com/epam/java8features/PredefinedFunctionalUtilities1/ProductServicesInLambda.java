package com.epam.java8features.PredefinedFunctionalUtilities1;

import java.util.function.Predicate;
import com.epam.java8features.PredefinedFunctionalUtilities3.Product;
import com.epam.java8features.PredefinedFunctionalUtilities3.ProductsData;

public class ProductServicesInLambda {
	public static void findProductsPriceMoreThan1000() {
		Predicate<Product> predicate = (product) -> product.getPrice() > 1000;
		new ProductsData().getDataSource().stream().filter(predicate).forEach(System.out::println);
	}

	public static void findproductsUnderElectronicCategory() {
		Predicate<Product> predicate = (product) -> product.getCategory().equalsIgnoreCase("electronics");
		new ProductsData().getDataSource().stream().filter(predicate).forEach(System.out::println);

	}

	public static void findProductsUnderElectronicsPriceLessThan100() {
		Predicate<Product> predicate1 = (product) -> product.getCategory().equalsIgnoreCase("electronics");
		Predicate<Product> predicate2 = product -> product.getPrice() < 100;
		new ProductsData().getDataSource().stream().filter(predicate1.and(predicate2)).forEach(System.out::println);
	}

	public static void findproductsUnderElectronicsPriceMoreThan100() {
		Predicate<Product> predicate1 = (product) -> product.getCategory().equalsIgnoreCase("electronics");
		Predicate<Product> predicate2 = product -> product.getPrice() > 100;
		new ProductsData().getDataSource().stream().filter(predicate1.and(predicate2)).forEach(System.out::println);
	}

	public static void findproductsUnderElectronicOrPriceMoreThan100() {
		Predicate<Product> predicate1 = prod -> prod.getCategory().equalsIgnoreCase("electronics");
		Predicate<Product> predicate2 = prod -> prod.getPrice() > 100;
		new ProductsData().getDataSource().stream().filter(predicate1.or(predicate2)).forEach(System.out::println);
	}
}
