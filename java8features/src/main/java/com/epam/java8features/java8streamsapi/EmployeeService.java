package com.epam.java8features.java8streamsapi;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EmployeeService extends EmployeeData {
	public static void countOfMaleAndFemaleEmployees() {
		Long maleCount = employeeList.stream()
				.filter((employeeList) -> employeeList.getGender().toLowerCase().equalsIgnoreCase("male"))
				.count();
		Long femaleCount = employeeList.stream()
				.filter((employeeList) -> employeeList.getGender().toLowerCase().equalsIgnoreCase("female"))
				.collect(Collectors.counting());
		System.out.println("No of Male Employees are : " + maleCount);
		System.out.println("No of Female Employees are : " + femaleCount);
	}

	public static void getAllDepartments() {
		Set<String> distinctDepartments = employeeList.stream().map(Employee::getDepartment)
				.collect(Collectors.toSet());
		System.out.println("The Departments are : " + distinctDepartments);
	}

	public static void getAverageAgeOfMaleAndFemaleEmployees() {
		System.out.println("Average age of male employees are : "
				+ employeeList.stream().filter(employeeList -> employeeList.getGender().equalsIgnoreCase("male")).mapToInt(Employee::getAge).average());
//						.collect(Collectors.averagingDouble(Employee::getAge)));
		System.out.println("Average age of female employees are : "
				+ employeeList.stream().filter(employeeList -> employeeList.getGender().equalsIgnoreCase("female"))
						.collect(Collectors.averagingDouble(Employee::getAge)));
	}

	public static void findMaxSalaryEmployeeDetails() {
		List<Employee> maxSalaryEmployeeDetails = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getSalary, TreeMap::new, Collectors.toList())).lastEntry()
				.getValue();
		if (maxSalaryEmployeeDetails.isEmpty()) {
			System.out.println("No Employees in the organisation");
		} else {
			System.out.println("Highest paid Employee Details are: " + maxSalaryEmployeeDetails);
		}
	}

	public static void findEmployeesJoinedAfter2015() {
		List<String> employeesJoinedAfter2015 = null;
		try {
			employeesJoinedAfter2015 = Optional
					.ofNullable(employeeList.stream().filter(employeeList -> employeeList.getYearOfJoining() > 2015)
							.map(Employee::getName).collect(Collectors.toList()))
					.filter(employees -> !employees.isEmpty()).orElseThrow(EmployeesNotFoundException::new);
			System.out.println("Employees joined after 2015 are : " + employeesJoinedAfter2015.toString());
		} catch (EmployeesNotFoundException e) {
			System.out.println("No Employees joined after 2015");

		}
		
	}

	public static void findCountOfEmployeesInEachDepartment() {
		Map<String, Long> eachDepartmentCount = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("Number Of Employees in each Department are : " + eachDepartmentCount);
	}

	public static void getEachDepartmentAverageSalary() {
		Map<String, Double> eachDeapartmentAvgSalary = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("Average Salary in each Department is : " + eachDeapartmentAvgSalary);
	}

	public static void findYoungestMaleEmployeeInProductionDepartment() {
		Map<Integer, List<Employee>> youngestMaleEmployeesInProductsionDepartment = employeeList.stream()
				.filter(employeeList -> employeeList.getDepartment().equalsIgnoreCase("Product Development")
						&& employeeList.getGender().equalsIgnoreCase("male"))
				.collect(Collectors.groupingBy(Employee::getAge, TreeMap::new, Collectors.toList()));
		if (youngestMaleEmployeesInProductsionDepartment.isEmpty()) {
			System.out.println("No employees in the organisation...So no youngest employee present..");
		} else {
			System.out.println("Youngest Employees in Product Development is :");
			youngestMaleEmployeesInProductsionDepartment.entrySet().stream().findFirst().get().getValue()
					.forEach(System.out::println);
		}
	}

	public static void findMostExperiencedEmployeeDetails() {
		Map<Integer, List<Employee>> mostExperiencedEmployeesDetails = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getYearOfJoining, TreeMap::new, Collectors.toList()));
		if (mostExperiencedEmployeesDetails.isEmpty()) {
			System.out.println("No employees in the organisation...So no most experienced employee present..");
		} else {
			System.out.println("Most Experienced Employees Details are :");
			mostExperiencedEmployeesDetails.entrySet().stream().findFirst().get().getValue()
					.forEach(System.out::println);
		}
	}

	public static void getMaleAndFemaleCountInSales() {
		int malesCountInSales = (int) employeeList.stream()
				.filter(employeeList -> employeeList.getDepartment().equalsIgnoreCase("Sales And Marketing")
						&& employeeList.getGender().equalsIgnoreCase("Male"))
				.count();
		int femalescountinsales = (int) employeeList.stream()
				.filter(employeeList -> employeeList.getDepartment().equalsIgnoreCase("Sales And Marketing")
						&& employeeList.getGender().equalsIgnoreCase("Female"))
				.count();
		System.out.println("Sales Department male employees count : " + malesCountInSales);
		System.out.println("Sales Department female employees count : " + femalescountinsales);
	}

	public static void getAverageSalaryOfMaleAndFemaleEmployees() {
		double averageSalaryOfMaleEmployees = employeeList.stream()
				.filter(employeeList -> employeeList.getGender().equalsIgnoreCase("Male"))
				.collect(Collectors.averagingDouble(Employee::getSalary));
		double avaerageSalaryOfFemaleEmployees = employeeList.stream()
				.filter(employeeList -> employeeList.getGender().equalsIgnoreCase("Female"))
				.collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println("Average salary of male employees is : " + averageSalaryOfMaleEmployees);
		System.out.println("Average salary of female employees is : " + avaerageSalaryOfFemaleEmployees);
	}

	public static void getNamesOfAllEmployeesInEachDepartment() {
		Map<String, List<String>> namesOfAllEmployeesInEachDepartment = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
		if (namesOfAllEmployeesInEachDepartment.isEmpty()) {
			System.out.println("No Employees in the Organisation..so no names to show..");
		} else {
			System.out.println("Names Of All Employees In Each Department are:");
			namesOfAllEmployeesInEachDepartment.entrySet().stream().forEach(System.out::println);
		}
	}

	public static void getAverageAndTotalSalaryOfOrganisation() {
		double totalSalary = employeeList.stream().mapToDouble(Employee::getSalary).average().getAsDouble();//.collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println("Total salary and average salary of whole organisation is : " + totalSalary + ","
				+  totalSalary / (employeeList.size()));
	}

	public static void getEmployeesAgeMoreThan25AndLessThanEqualTo25() {
		List<Employee> employeesListAgeLessThanEqualTo25 = employeeList.stream()
				.filter(employeeList -> employeeList.getAge() <= 25).collect(Collectors.toList());
		Consumer<List<Employee>> display = list -> list.stream().forEach(a -> System.out.println(a));
		List<Employee> employeesListAgeGreaterThan25 = employeeList.stream()
				.filter(employeeList -> employeeList.getAge() > 100).collect(Collectors.toList());
		if (employeesListAgeLessThanEqualTo25.isEmpty()) {
			System.out.println("No Employees whose age is less than equal to 25");
		} else {
			System.out.println("Employees Under 25 years old :");
			display.accept(employeesListAgeLessThanEqualTo25);
		}
		if (employeesListAgeGreaterThan25.isEmpty()) {
			System.out.println("No Employees whose age is greater than 25");
		} else {
			System.out.println("Employees Greater than 25 years old :");
			display.accept(employeesListAgeGreaterThan25);
		}
	}

	public void getOldestEmployeeDetails() {
		Map<Integer, List<Employee>> oldestEmployeesList = employeeList.stream()
				.filter(employeeList -> employeeList.getDepartment().equalsIgnoreCase("Product Development"))
				.collect(Collectors.groupingBy(Employee::getAge, TreeMap::new, Collectors.toList()));
		if (oldestEmployeesList.isEmpty()) {
			System.out.println("No Employees in the Organisation..so no employees to show..");
		} else {
			System.out.println("Oldest employee in the organisation is ");
			oldestEmployeesList.entrySet().stream().reduce((one, two) -> two).get().getValue()
					.forEach(System.out::println);
		}
	}
}