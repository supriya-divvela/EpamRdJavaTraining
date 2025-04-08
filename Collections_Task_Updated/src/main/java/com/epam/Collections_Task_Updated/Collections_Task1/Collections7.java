package com.epam.Collections_Task_Updated.Collections_Task1;
import java.util.*;
class Collections7
{
	public static void main(String[] args)
	{
		TreeMap<Employee,String> tm=new TreeMap<Employee,String>((e1,e2)->e1.name.compareTo(e2.name));
		tm.put(new Employee(1,"Supriya"),"Java");
		tm.put(new Employee(2,"Pavan"),"Python");
		tm.put(new Employee(3,"Harshu"),"C++");
		tm.put(new Employee(4,"Keerthi"),"Dot Net");
		for (Map.Entry<Employee, String> entry : tm.entrySet()) 
		{
            System.out.println("Id : " + entry.getKey().id+ " Name : "+entry.getKey().name+" Skill : "+ entry.getValue());
        }
	}
}