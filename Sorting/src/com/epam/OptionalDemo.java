package com.epam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OptionalDemo {
	public String getMyDefault() {
	    System.out.println("Getting Default Value");
	    return "Default Value";
	}
	public void ok() {
		String text ="ok";
	
	    System.out.println("Using orElseGet:");
	    String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
	    System.out.println(defaultText);
	    System.out.println("Using orElse:");
	    defaultText = Optional.ofNullable(text).orElse(getMyDefault());
	    System.out.println( defaultText);
	}
	public static void main(String[] args) {
//		OptionalDemo op=new OptionalDemo();
//		System.out.println(Optional.ofNullable(null).get());
//		op.ok();
//		 System.out.println(Optional.of(null));
//		    String name = opt.get();
		 Scanner sc=new Scanner(System.in);
		 int n=Integer.parseInt(sc.nextLine());
		 List<Integer> arr=Arrays.stream(sc.nextLine().split(" ")).map(i->Integer.parseInt(i)).collect(Collectors.toList());
		 System.out.println(arr.toString());
	}
}
