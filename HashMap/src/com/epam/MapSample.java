package com.epam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class MapSample {
	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "Java");
		map.put(2, "Python");
		map.put(3, "c++");
		map.put(4, ".net");
		Iterator<Entry<Integer, String>> i = map.entrySet().iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}

	}
}
