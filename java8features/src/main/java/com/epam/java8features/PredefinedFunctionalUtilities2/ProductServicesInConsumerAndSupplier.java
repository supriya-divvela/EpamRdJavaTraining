package com.epam.java8features.PredefinedFunctionalUtilities2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.epam.java8features.PredefinedFunctionalUtilities3.Product;
import com.epam.java8features.PredefinedFunctionalUtilities3.ProductsData;

public class ProductServicesInConsumerAndSupplier {
	public static void printProducts(Product product) {
		try (Scanner scanner = new Scanner(System.in)) {
			Consumer<Product> consumer = (p) -> {
				String input = scanner.nextLine();
				if (input.equalsIgnoreCase("file")) {
					try (FileWriter file = new FileWriter(
							"C:\\Users\\Supriya_Divvela\\Desktop\\versioncontrol\\hello.txt")) {
						file.write(p.toString());
					} catch (IOException e) {
						System.out.println(e);
					}
				} else if (input.equalsIgnoreCase("console")) {
					System.out.println(p);
				} else {
					System.out.println("Invalid Input.Please try again with file/console as input");
				}
			};
			consumer.accept(product);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateProductGrade() {
		Consumer<Product> consumer = product -> {
			if (product.getPrice() > 3000) {
				product.setCategory("Premium");
				System.out.println(product);
			}
		};
		new ProductsData().getDataSource().stream().forEach(product -> consumer.accept(product));
	}

	public static void findProductsPriceMoreThan3000() {
		Consumer<Product> consumer = product -> {
			if (product.getPrice() > 3000) {
				product.setName("*" + product.getName());
				System.out.println(product);
			}
		};
		new ProductsData().getDataSource().stream().forEach(product -> consumer.accept(product));
	}

	public static void getPremiumProducts() {
		Consumer<Product> consumer = product -> {
			if (product.getCategory().equalsIgnoreCase("premium") && product.getName().contains("*")) {
				System.out.println(product);
			}
		};
		new ProductsData().getDataSource().stream().forEach(product -> consumer.accept(product));
	}

	public static void getRandomProduct() {
		Supplier<Product> supplier = () ->{
			return  new ProductsData().getDataSource().get((int)Math.random()*(new ProductsData().getDataSource().size()));
		};
		System.out.println(supplier.get());
	}

	public static void getRandomOTP() {
		Supplier<Integer> supplier = () -> (int) (Math.random() * (9000) + 1000);
		System.out.println(supplier.get());
	}
}
