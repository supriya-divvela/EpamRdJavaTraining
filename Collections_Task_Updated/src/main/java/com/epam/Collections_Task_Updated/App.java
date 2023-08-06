package com.epam.Collections_Task_Updated;
import java.util.*;
public class App {
	public static void main(String[] args)
	{
		TreeMap<Integer,String> map=new TreeMap<Integer,String>((I1,I2)->(I1<I2)?1:(I1>I2)?-1:0);    
	      map.put(100,"Amit");    
	      map.put(102,"Ravi");    
	      map.put(101,"Vijay");    
	      map.put(103,"Rahul");      
	      for(Map.Entry<Integer,String> m:map.entrySet()){    
	       System.out.println(m.getKey()+" "+m.getValue());    
	      }     
	}
}
