package com.epam.java8features.java8streamsapi;

public class EmployeeStream {
	public static void main(String[] args) {
		EmployeeService e = new EmployeeService();
		EmployeeService.countOfMaleAndFemaleEmployees();
		EmployeeService.getAllDepartments();
		EmployeeService.getAverageAgeOfMaleAndFemaleEmployees();
		EmployeeService.findMaxSalaryEmployeeDetails();
		EmployeeService.findEmployeesJoinedAfter2015();
		EmployeeService.findCountOfEmployeesInEachDepartment();
		EmployeeService.getEachDepartmentAverageSalary();
		EmployeeService.findYoungestMaleEmployeeInProductionDepartment();
		EmployeeService.findMostExperiencedEmployeeDetails();
		EmployeeService.getMaleAndFemaleCountInSales();
		EmployeeService.getAverageSalaryOfMaleAndFemaleEmployees();
		EmployeeService.getNamesOfAllEmployeesInEachDepartment();
		EmployeeService.getAverageAndTotalSalaryOfOrganisation();
		EmployeeService.getEmployeesAgeMoreThan25AndLessThanEqualTo25();
		e.getOldestEmployeeDetails();
	}
}