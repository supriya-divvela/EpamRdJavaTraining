package com.epam.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListDemo {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		Iterator<Integer> it=list.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//		for(Integer i:list) {
//			System.out.println(i);
//		}
		Map<Integer,String> map=new HashMap<>();
		map.put(1, "hello");
//		for(Map.Entry<Integer, String> entry:map.entrySet()) {
//			System.out.println(entry.getKey()+" "+entry.getValue()+" "+entry.toString());
//		}
		Set<Integer> mapKeys=map.keySet();
		Iterator mapIt=	mapKeys.iterator();
		while(mapIt.hasNext()) {
			Integer s= (Integer) mapIt.next();
			System.out.println(s+" "+map.get(s));
		}
		System.out.println();
	}
}
