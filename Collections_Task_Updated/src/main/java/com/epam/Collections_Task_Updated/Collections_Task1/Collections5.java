package com.epam.Collections_Task_Updated.Collections_Task1;
import java.util.*;
class Collections5
{
	public static void main(String[] args)
	{
		TreeSet<Employee> ts=new TreeSet<Employee>((e1,e2)->e1.name.compareTo(e2.name));
		ts.add(new Employee(1,"Supriya"));
		ts.add(new Employee(2,"Pavan"));
		ts.add(new Employee(3,"Harshu"));
		ts.add(new Employee(4,"Keerthi"));
		for(Employee ele:ts)
        	System.out.println("Id : "+ele.id+" Name is : "+ele.name);
	}
}