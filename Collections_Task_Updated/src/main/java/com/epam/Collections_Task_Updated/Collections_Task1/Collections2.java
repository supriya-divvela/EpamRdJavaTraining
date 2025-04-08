package com.epam.Collections_Task_Updated.Collections_Task1;

import java.util.*;
import java.lang.*;
class Collections2{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		String[] num=sc.nextLine().split(" ");
		ArrayList<Integer> al=new ArrayList<>();
		for(int i=0;i<num.length;i++)
		{
			al.add(Integer.parseInt(num[i]));
		}
		Collections.sort(al,(I1,I2)->(I1<I2)?1:(I1>I2)?-1:0);	
		System.out.println(al);
	}
}
