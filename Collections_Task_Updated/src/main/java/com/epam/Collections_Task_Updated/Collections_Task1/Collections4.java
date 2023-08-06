package com.epam.Collections_Task_Updated.Collections_Task1;

import java.util.*;
class Collections4
{
	public static void main(String[] args)
	{
		TreeSet<Integer> ts=new TreeSet<Integer>();
		Scanner sc=new Scanner(System.in);
		String[] num=sc.nextLine().split(" ");
		for(int i=0;i<num.length;i++)
		{
			ts.add(Integer.parseInt(num[i]));
		}
		TreeSet<Integer> res = (TreeSet<Integer>)ts.descendingSet();
		System.out.println("Ascending Order : "+ts);
		System.out.println("Descending Order : "+res);
	}
}