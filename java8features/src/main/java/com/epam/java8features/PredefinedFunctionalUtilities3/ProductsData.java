package com.epam.java8features.PredefinedFunctionalUtilities3;

import java.util.Arrays;
import java.util.List;

public class ProductsData {
	static List<Product> products = Arrays.asList(new Product("pen", 5, "stationary", "a"),
			new Product("laptop", 4000, "electronics", "b"), new Product("rings", 200, "jewellary", "c"),
			new Product("bat", 40000, "sports", "d"));

	public List<Product> getDataSource() {
		return products;
	}
}
