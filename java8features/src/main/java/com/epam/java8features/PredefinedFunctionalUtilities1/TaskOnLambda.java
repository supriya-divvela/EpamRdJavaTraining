package com.epam.java8features.PredefinedFunctionalUtilities1;

public class TaskOnLambda {
	public static void main(String[] args) {
		ProductServicesInLambda.findproductsUnderElectronicCategory();
		ProductServicesInLambda.findProductsPriceMoreThan1000();
		ProductServicesInLambda.findProductsUnderElectronicsPriceLessThan100();
		ProductServicesInLambda.findproductsUnderElectronicsPriceMoreThan100();
		ProductServicesInLambda.findproductsUnderElectronicOrPriceMoreThan100();
	}
}
