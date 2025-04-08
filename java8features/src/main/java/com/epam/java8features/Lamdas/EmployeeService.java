package com.epam.java8features.Lamdas;

import java.util.*;

class EmployeeService {
	public static void sortEmployeesByName() {
		List<Employee> employeeList = EmployeeData.getDataSource();
		Collections.sort(employeeList, (employee1, employee2) -> employee1.getName().compareTo(employee2.getName()));
		employeeList.stream().forEach(System.out::println);
	}

	public static void sortEmployeesByNameInDescendingOrder() {
		List<Employee> employeeList = EmployeeData.getDataSource();
		Collections.sort(employeeList, (employee1, employee2) -> employee2.name.compareTo(employee1.name));
		employeeList.stream().forEach(System.out::println);
	}

	public static void sortEmployeesByNameInTreeset() {
		TreeSet<Employee> employeeList = new TreeSet<Employee>(
				(employee1, employee2) -> employee1.name.compareTo(employee2.name));
		employeeList.addAll(EmployeeData.getDataSource());
		employeeList.stream().forEach(System.out::println);
	}

	public static void sortEmployeesByNameInTreemap() {
		TreeMap<Employee, String> employees = new TreeMap<Employee, String>(
				(employee1, employee2) -> employee2.name.compareTo(employee1.name));
		employees.put(new Employee(1, "Supriya"), "Java");
		employees.put(new Employee(2, "Pavan"), "Python");
		employees.put(new Employee(3, "Harshu"), "C++");
		employees.put(new Employee(4, "Keerthi"), "Dot Net");
		employees.entrySet().stream()
				.forEach(employee -> System.out.println(employee.getKey() + " : " + employee.getValue()));
	}

	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(Map<K, V> map) {
		Comparator<K> valueComparator = (key1, key2) -> map.get(key1).compareTo(map.get(key2));
		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
	}

	public static void sortTreemapByValues() {
		Map<Integer, String> treemap = new TreeMap<Integer, String>();
		treemap.put(1, "Java");
		treemap.put(2, "Python");
		treemap.put(3, "C++");
		treemap.put(4, "Dot Net");
		Map<Integer, String> sortedMap = sortByValues(treemap);
		sortedMap.entrySet().stream()
				.forEach(employee -> System.out.println(employee.getKey() + " : " + employee.getValue()));
	}

	public static void main(String[] args) {
		EmployeeService.sortEmployeesByName();
		EmployeeService.sortEmployeesByNameInDescendingOrder();
		EmployeeService.sortEmployeesByNameInTreemap();
		EmployeeService.sortEmployeesByNameInTreeset();
		EmployeeService.sortTreemapByValues();
	}
}