package com.epam.shallowanddeepcopy;

import java.util.ArrayList;
import java.util.List;

public class ShallowCopy {
	public static void main(String[] args){
		Employee employee1 = new Employee("James", 21);
		System.out.println(employee1);
		Employee employee2=employee1;
		System.out.println(employee2);
		employee1.setAge(30);
		System.out.println(employee1 + " " + employee2);
		List<Integer> list1 = new ArrayList<>();
		list1.add(10);
		list1.add(20);
		list1.add(30);
		list1.add(40);
		System.out.println(list1);
		List<Integer> list2 = list1;
		System.out.println(list2);
		list2.set(2, 50);
		list2.add(60);
		System.out.println(list1 + " " + list2);
	}
}
