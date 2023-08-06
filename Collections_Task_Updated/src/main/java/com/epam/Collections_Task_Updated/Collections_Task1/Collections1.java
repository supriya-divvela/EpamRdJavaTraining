package com.epam.Collections_Task_Updated.Collections_Task1;
import java.util.*;
public class Collections1 
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		HashSet<Integer> hs=new HashSet<>();
		String[] num=sc.nextLine().split(" ");
		for(int i=0;i<num.length;i++)
		{
			hs.add(Integer.parseInt(num[i]));
		}
		ArrayList<Integer> al=new ArrayList<>(hs);
		Collections.sort(al,(I1,I2)->(I1<I2)?1:(I1>I2)?-1:0);
		if(al.size()>1)
		{
			System.out.println("Second Biggest Element : "+al.get(1));
		}
		else
		{
			System.out.println("Insufficient Elements");
		}
	}
}