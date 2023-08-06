package com.epam.java8features.PredefinedFunctionalUtilities2;

import com.epam.java8features.PredefinedFunctionalUtilities3.ProductsData;

public class TaskOnConsumerAndSupplier {
	public static void main(String[] args) {
		ProductServicesInConsumerAndSupplier.printProducts(new ProductsData().getDataSource().get(0));
		ProductServicesInConsumerAndSupplier.updateProductGrade();
		ProductServicesInConsumerAndSupplier.findProductsPriceMoreThan3000();
		ProductServicesInConsumerAndSupplier.getPremiumProducts();
		ProductServicesInConsumerAndSupplier.getRandomProduct();
		ProductServicesInConsumerAndSupplier.getRandomOTP();
	}
}
