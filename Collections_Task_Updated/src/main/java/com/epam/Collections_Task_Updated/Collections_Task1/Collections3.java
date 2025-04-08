package com.epam.Collections_Task_Updated.Collections_Task1;
import java.util.*;
class Collections3{
	public static void main(String[] args)
	{
		ArrayList<Employee> al=new ArrayList<Employee>();
		al.add(new Employee(1,"Supriya"));
		al.add(new Employee(2,"Pavan"));
		al.add(new Employee(3,"Harshu"));
		al.add(new Employee(4,"Keerthi"));
		Collections.sort(al,(e1,e2)->e1.name.compareTo(e2.name));
		for(int i=0;i<al.size();i++)
		{
			System.out.println("Id : "+al.get(i).id+" "+"Name is : "+al.get(i).name);
		}
	}
}