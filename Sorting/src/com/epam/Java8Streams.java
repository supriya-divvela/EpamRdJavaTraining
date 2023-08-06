package com.epam;

import java.util.Arrays;
import java.util.Collections;

//@FunctionalInterface
interface interf {
//	public int add(int a, int b);
	public int add(int a, int b, int c);
}

public class Java8Streams{
	public int add(int a, int b,int c) {
		return a + b;
	}

//	public static int add(int a, int b, int c) {
//		return a + b;
//	}

	public static void main(String[] args) throws Exception {
//		Arrays.asList(1,2,3,4).stream().filter(i->i%2==0).forEach(System.out::println);
//		Arrays.asList(1,2,3,4).stream().map(i->i+"").filter(i->i.startsWith("1")).forEach(System.out::println);
//		System.out.println(Arrays.asList().stream().findFirst().orElseGet(()->"Empty Array List"));
//		System.out.println(Arrays.asList().stream().findFirst().orElseThrow(()->new Exception("ok")));
//		Set<Integer> set=new HashSet<>();
//		System.out.println(Arrays.asList(1,2,2,33,4,5,65,23,2,2,3,43,33,4,5,8).stream().collect(Collectors.toCollection(TreeSet::new)));
//		System.out.println(Arrays.asList(1,2,2,33,4,5,65,23,2,2,3,43,33,4,5,8).stream().filter(i->!set.add(i)).collect(Collectors.toSet()));
//		System.out.println(Arrays.asList(1,2,34,4,5,6,6).stream().sorted(Collections.reverseOrder()).reduce((a,b)->b).get());
//		String input = "Java Hungry Blog Alive is Awesome";
//		System.out.println( input.chars().mapToObj(s->Character.toLowerCase(Character.valueOf((char)s))).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())).entrySet().stream().filter(entry->entry.getValue()==1L).map(entry->entry.getKey()).findFirst().get());
//		new Random().ints(100,1,10).forEach(System.out::println);
//		System.out.println(Arrays.asList(1,2,3,4,5).stream().mapToInt(i->i).max().getAsInt());
//		System.out.println(Arrays.asList("hello","hai","java").stream().min(Comparator.comparing(String::length)).get());
//		Stream.concat(Arrays.asList(1,2,3).stream(), Arrays.asList(1,2,3).stream()).forEach(System.out::println);
//		String s="Hii this is java";
//		Arrays.stream(s.split(" ")).forEach(System.out::println);
//		List<Integer> list=Arrays.asList(1,2,2,33,4,5,65,23,2,2,3,43,33,4,5,8);
//		System.out.println(list.stream().filter(i->Collections.frequency(list,i)==1).collect(Collectors.toList()));
//		String s="supriya";
//		System.out.println(s.chars().mapToObj(i->(char)i).reduce("",(a,b)->b+a,(a,b)->b+a));
//		IntStream.range(1, 30).forEach(System.out::print);
//		IntStream.range(0, "supriya".length()).mapToObj(i->"supriya".charAt("supriya".length()-i-1)+"").forEach(System.out::print);
//		String s="@#$%^&*()(*&^HJHCVB         NMHGSwerty12     345678gv%^&*(^%#$vDgV#$%";
//		String s1=s.replaceAll("[^a-zA-Z0-9]","");
//		System.out.println(s1);
//		Map<Integer,String> map=new HashMap<>();
//		map.put(1, "hi");
//		map.put(2, "ok");
//		map.put(-3,"nul");
//		map.put(12, "ok");
//		Set<Integer> set=map.keySet();
//		Iterator<Integer> it=set.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//		for(Map.Entry<Integer,String> entry:map.entrySet()) {
//			System.out.println(entry.getKey());
//		}
//		map.entrySet().stream().forEach(entry->System.out.println(entry.getKey()+" "+entry.getValue()));
//		Map<Integer,String> map1=map.entrySet().stream().sorted((entry1,entry2)->entry1.getValue().compareTo(entry2.getValue())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1, e2) -> e2,LinkedHashMap::new));
//		for(Map.Entry<Integer,String> entry:map1.entrySet()) {
//			System.out.println(entry.getKey()+" "+entry.getValue());
//		}
//		map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(entry->System.out.println(entry.getKey()+" "+entry.getValue()));
//		Arrays.asList(1,2,3,4,4,5,6,2,3,1,4).stream().distinct().forEach(System.out::println);
//		System.out.println(Arrays.asList(1,2,3,4,4,5,6,2,3,1,4).stream().sorted().findFirst().get());
//		System.out.println(Arrays.asList(1,2,3,4,4,5,6,2,3,1,4).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));
//		System.out.println(Arrays.asList(1,2,3,4,4,5,6,2,3,1,4).stream().mapToInt(i->i).sum());
//		IntSummaryStatistics stats = Arrays.asList(1,2,3,4,5).stream().mapToInt((x) -> x).summaryStatistics();
//
//		System.out.println("Highest number in List : " + stats.getMax());
//		System.out.println("Lowest number in List : " + stats.getMin());
//		System.out.println("Sum of all numbers : " + stats.getSum());
//		System.out.println("Average of all numbers : " + stats.getAverage());
//		System.out.println("Count of all numbers : " + stats.getCount());
//		System.out.println(Arrays.asList("hell","hii","ooj").stream().collect(Collectors.joining(",","[","]")));
//		Stream.of("a", "b", "c").forEach(System.out::println);
//		Stream.builder().add("a").add("b").build().forEach(System.out::println);
//		Stream.generate(() -> 10).limit(10).forEach(System.out::println);
//		System.out.println(Arrays.asList(1,2,34,4,5,6,6).stream().sorted(Collections.reverseOrder()).reduce(0,(a,b)->b));
//		System.out.println("hiiiiokok".chars().mapToObj(i->(char)i+"").reduce("",(a,b)->b+a));
//		String str = "Aniruddh";
//		Stream.iterate(str.length() - 1, n -> n >= 0, n -> n - 1).map(str::charAt).forEach(System.out::print);
//		List<Integer> list1=new ArrayList<>(Arrays.asList(1,2,2,1));
//		List<Integer> list2=new ArrayList<>(Arrays.asList(2,1));
//		List<Integer> list3=new ArrayList<>();
//		Set<Integer> set=new HashSet<>(list1);
//		for(Integer i:set) {
//			for(int j=0;j<Math.min(Collections.frequency(list1,i),Collections.frequency(list2,i));j++) {
//				list3.add(i);
//			}
//		}
//		System.out.println(list3.toString());
//		System.out.println(Arrays.asList("hello","hii","ok","hello").stream().collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())));
//		Java8Streams j=new Java8Streams();
//		Java8Streams j2=(Java8Streams) j.clone();
//		System.out.println(Arrays.asList("hello","hii","ok").stream().sorted(Collections.reverseOrder(Comparator.comparing(String::length))).collect(Collectors.toList()).get(0));
//		int[] a=new int[] {1,2,3};
//		Arrays.stream(a).forEach(i->System.out.print((i)));
//		System.out.println(l);
//		IntSummaryStatistics stats = Stream.of(1,2,3,4,5).mapToInt((x) -> x).summaryStatistics();
//		System.out.println(stats.getMax());
//		List<Customer> list=Arrays.asList(new Customer(1,"hello"),new Customer(10,"ok"),new Customer(3,"dfg"),new Customer(20,"jii"));
//		Collections.sort(list,(c1,c2)->c1.getName().compareToIgnoreCase(c2.getName()));
//		System.out.println(list);
//		Java8Streams java8Streams=new Java8Streams();
//		interf i = java8Streams::add;
//		System.out.println(i.add(1, 2, 3));
//		Queue<Integer> q=new PriorityQueue<>();
//		q.add(null);
//		q.add(1);
//		q.add(1);
//		System.out.println(q);
	}
}

//interface A{
//	static void m1() {
//		System.out.println("A");
//	}
//}
//interface B{
//	default void m1() {
//		System.out.println("B");
//	}
//}