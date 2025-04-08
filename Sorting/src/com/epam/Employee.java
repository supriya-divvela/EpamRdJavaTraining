package com.epam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Employee implements Comparable<Employee>,Cloneable {
	@Override
	public int hashCode() {
		return Objects.hash(branch, id, salary);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(branch, other.branch) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}

	private Integer id;
	private String branch;
	private int salary;
	@Override  
	public Object clone() throws CloneNotSupportedException   
	{   
	return super.clone();   
	}   
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setName(String name) {
		this.branch = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Employee(Integer id, String name, int salary) {
		super();
		this.id = id;
		this.branch = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + branch + ", salary=" + salary + "]";
	}

	@Override
	public int compareTo(Employee o) {
		return Double.compare(this.id, o.getId());
	}

	public static void main(String[] args) {
		List<Employee> footballTeam = new ArrayList<>();
		Employee Employee1 = new Employee(59, "John", 28);
	    Employee Employee2 = new Employee(67, "Roger", 22);
	    Employee Employee3 = new Employee(45, "Steven", 24);
	    footballTeam.add(Employee1);
	    footballTeam.add(Employee2);
	    footballTeam.add(Employee3);
	    Collections.sort(footballTeam);
	    System.out.println("Before Sorting : " + footballTeam);
	    Collections.sort(footballTeam,new Comparator<Employee>() {
	    	@Override
	    	public int compare(Employee e1,Employee e2) {
	    		return e2.salary>e1.getSalary()? 1 : -1;
	    	}
		});
	    System.out.println("After Sorting : " + footballTeam);
	}
}
