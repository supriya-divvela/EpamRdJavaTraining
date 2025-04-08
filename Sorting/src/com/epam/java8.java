package com.epam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class java8 {
	static boolean b;

	public final static void main(String[] args) throws Exception {
		String input = "Welcome to Java world Welcome";
		System.out.println(Arrays.stream(input.split(" ")).max(Comparator.comparing(String::length))
		        .get());
//		Set<Integer> s=new HashSet<>();
//		System.out.println(Arrays.asList(1,2,3,4,5,1,2,3,4,5,1,2,3,4,5).stream().filter(i->Collections.frequency(Arrays.asList(1,2,3,4,5,1,2,3,4,5,1,2,3,4,5), i)<2).collect(Collectors.toSet()));
//		System.out.println(Arrays.asList(10,15,8,49,25,98,98,32,15).stream().filter(i->!s.add(i)).collect(Collectors.toList()));
//		int arr[]= {};
//		System.out.println(Arrays.stream(arr).min().getAsInt());
//		Optional<String> opt = Optional.ofNullable(null);
//		String name = opt.get();
//		System.out.println(Arrays.asList("ok").stream().map(i->i.toString()).min(Comparator.comparing(String::length)).orElse("No Values"));
//		Optional of=Optional.ofNullable(null);
//		System.out.println(of);
//		Arrays.stream(input.split(" "))
//		        .sorted(Comparator.comparing(String::length))
//		        .forEach(System.out::println);
//		System.out.println();
		int[] arr = {5, 7, 5, 7, 5, 2};
//		System.out.println(Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
//		        .entrySet().stream().max((Map.Entry.comparingByKey())).get().getKey());
//		byte b=(byte) 256;
//		System.out.println(b);
//		System.out.println(8 % 2 == 0 ? "even number" : "odd number");
//		String str = "hello";
//		str.chars().boxed().map(t ->(char)t.intValue()).forEach(System.out::println);
//		Arrays.asList(1,2,3,4,11,12,13).stream().map(i->i+"").filter(i->i.startsWith("1")).forEach(System.out::println);
//		System.out.println(Arrays.asList().stream().findAny().orElseGet(()->"Empty ArrayList"));
//		System.out.println(Arrays.asList(1,2,3,4,5,6,78,1,2,32,1,42,3,2,5,3,1,2,3,4).stream().collect(Collectors.toCollection(TreeSet::new)));
//		System.out.println(Arrays.asList(1,2,3,4,5,6,78,1,2,32,1,42,3,2,5,3,1,2,3,4).stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.reverseOrder()))));
//		System.out.println(Arrays.asList(1,2,3,4,5,6,78,1,2,32,1,42,3,2,5,3,1,2,3,4).stream().collect(Collectors.toSet()));
//		Set<Integer> set=new HashSet<>();
//		System.out.println(Arrays.asList(1,2,4,5,6,5,4,3,2,1,78,1,2,32,1,42,2,5,3,1,2,3,4).stream().filter(s->!set.add(s)).collect(Collectors.toCollection(LinkedHashSet::new)));
//		System.out.println(Arrays.asList(1,2,4,5,6,5,4,3,2,1,78,1,2,32,1,42,2,5,3,1,2,3,4).stream().sorted((i1,i2)->i2.compareTo(i1)).collect(Collectors.toList()));
//		System.out.println(Arrays.asList(1,2,34,4,5,6,6).stream().sorted((i1,i2)->i2.compareTo(i1)).reduce((a,b)->b).get());
//		System.out.println(Arrays.stream("epam system".split("")).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())));
//		new Random().ints(10,20).limit(15).forEach(System.out::println);
//		System.out.println(Arrays.asList(1,2,34,4,5,6,6).stream().mapToInt(i->i).summaryStatistics());
//		System.out.println(Arrays.asList(1,2,3,4,5).stream().map(i->i).min(Comparator.comparing(Integer::new)).get());
//		System.out.println(Arrays.asList(1,2,3,4,4).stream().mapToInt(i->i).min());
//		String s1="supus";
//		String s2=new StringBuffer(s1).reverse().toString();
//		System.out.println(s1==(s2));
//		Employee e1=new Employee();
//		System.out.println(e1);
//		System.out.println((Employee)e1.clone());
//		System.out.println((Employee)Class.forName("com.epam.Employee").newInstance());
//		System.out.println(Employee.class.getConstructor().newInstance());
//		System.out.println(Employee.class.getDeclaredConstructor().newInstance());
//		System.out.println(Employee.class.newInstance());
//		System.out.println(Stream.of(1,2,3,4,1,2,3,4).collect(Collectors.toSet()));
//		System.out.println(Stream.of("Sup","pav","Pav","sup","SUp").map(word->word.toLowerCase()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));
		System.out.println(Stream.of("supriya","supriya","pavan","pavan","latha").collect(Collectors.groupingBy(Function.identity(),TreeMap::new,Collectors.counting())));
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorService fixedPool = Executors.newFixedThreadPool(2);
		ExecutorService executorService = Executors.newCachedThreadPool();
		ScheduledExecutorService scheduledExecService = Executors.newScheduledThreadPool(1);
		Future<Employee> result = executorService.submit(new Callable<Employee>() {

			@Override
			public Employee call() throws Exception {
				// TODO Auto-generated method stub
				return new Employee();
			}
		});
		System.out.println(result.get()+"Hello");
	}
}
