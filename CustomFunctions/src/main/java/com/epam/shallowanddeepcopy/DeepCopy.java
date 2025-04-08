package com.epam.shallowanddeepcopy;

import java.util.ArrayList;
import java.util.List;

public class DeepCopy {
	public static void main(String[] args) {
		Employee employee1 = new Employee("Kiara", 27);
		System.out.println(employee1);
		Employee employee2 = null;
		try {
			employee2 = (Employee) employee1.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(employee2);
		employee1.setAge(29);
		System.out.println(employee1 + " " + employee2);
		Employee employee3 = new Employee(employee2);
		System.out.println(employee3);
		employee2.setAge(40);
		System.out.println(employee2 + " " + employee3);
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(10);
		list1.add(20);
		list1.add(30);
		list1.add(40);
		System.out.println(list1);
		List<Integer> list2 = new ArrayList<Integer>(list1);
		System.out.println(list2);
		list2.set(2, 50);
		list2.add(60);
		System.out.println(list1 + " " + list2);
	}
}
