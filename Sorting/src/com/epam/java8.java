package com.epam;

import java.util.Arrays;
import java.util.Comparator;

public class java8 {
	static boolean b;

	public final static void main(String[] args) {
//		byte b=(byte) 259;
//		System.out.println(b);
//		System.out.println(8 % 2 == 0 ? "even number" : "odd number");
		String str = "hello";
//		str.chars().boxed().map(t ->(char)t.intValue()).forEach(System.out::println);
//		Arrays.asList(1,2,3,4).stream().map(i->i+"").filter(i->i.startsWith("1")).forEach(System.out::println);
//		System.out.println(Arrays.asList().stream().findAny().orElseGet(()->"Empty ArrayList"));
//		System.out.println(Arrays.asList(1,2,3,4,5,6,78,1,2,32,1,42,3,2,5,3,1,2,3,4).stream().collect(Collectors.toCollection(TreeSet::new)));
//		System.out.println(Arrays.asList(1,2,3,4,5,6,78,1,2,32,1,42,3,2,5,3,1,2,3,4).stream().collect(Collectors.toSet()));
//		Set<Integer> set=new HashSet<>();
//		System.out.println(Arrays.asList(1,2,4,5,6,5,4,3,2,1,78,1,2,32,1,42,2,5,3,1,2,3,4).stream().filter(s->!set.add(s)).collect(Collectors.toCollection(LinkedHashSet::new)));
//		System.out.println(Arrays.asList(1,2,4,5,6,5,4,3,2,1,78,1,2,32,1,42,2,5,3,1,2,3,4).stream().sorted((i1,i2)->i2.compareTo(i1)).collect(Collectors.toList()));
//		System.out.println(Arrays.asList(1,2,34,4,5,6,6).stream().sorted((i1,i2)->i2.compareTo(i1)).reduce((a,b)->b).get());
//		System.out.println("epam system".chars().mapToObj(i->(char)i+"").collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.toSet())));
//		new Random().ints(100,110).limit(5).forEach(System.out::println);
//		System.out.println(Arrays.asList(1,2,34,4,5,6,6).stream().mapToInt(i->i).sum());
		System.out.println(Arrays.asList(1,2,3,4,5).stream().map(i->i).max(Comparator.comparing(Integer::new)).get());
	}
}
