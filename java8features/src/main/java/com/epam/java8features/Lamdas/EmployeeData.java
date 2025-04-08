package com.epam.java8features.Lamdas;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
	public static List<Employee> getDataSource() {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee(1, "Supriya"));
		employeeList.add(new Employee(2, "Pavan"));
		employeeList.add(new Employee(3, "Harshu"));
		employeeList.add(new Employee(4, "Keerthi"));
		return employeeList;
	}
}
