package com.epam.Collections_Task_Updated.Collections_Task1;
import java.util.*;
class Collections6
{
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map)
	{
    		Comparator<K> valueComparator = new Comparator<K>()
    		{
      			public int compare(K k1, K k2) {
        			return map.get(k1).compareTo(map.get(k2));
      			}
    		};
		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
    	return sortedByValues;
	}
	public static void main(String[] args){
		Map<Integer,String> tm=new TreeMap<Integer,String>();
		tm.put(1,"Java");
		tm.put(2,"Python");
		tm.put(3,"C++");
		tm.put(4,"Dot Net");
		Map sortedMap = sortByValues(tm);
		Set set = sortedMap.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext())
		{
  			Map.Entry me = (Map.Entry)i.next();
  			System.out.println(me.getKey() + " : "+me.getValue());
		}
	}
}