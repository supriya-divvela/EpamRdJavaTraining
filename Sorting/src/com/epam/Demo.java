package com.epam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
interface ok {
	public int doIt();

}

public class Demo {
//	private final int x;
	
	public static void main(String[] args) throws Exception {
		ok okok = () -> {
			return 3;
		};
//		Optional<String> empty = Optional.ofNullable("ok");
//	    System.out.println(empty.get());
//	    System.out.println( empty.isPresent());
		practise p=new practise();
//		Class c=Class.forName("com.epam.practise");
//		System.out.println(c);
//		@SuppressWarnings("deprecation")
//		practise p2=practise.class.newInstance();
//		practise p3=(practise) p.clone();
//		System.out.println(p+" "+p2+" "+p3);
//		Arrays.asList(1,2,3,4,5,6).stream().filter(number->number%2==0).forEach(System.out::println);
//		Arrays.asList(1,2,3,4,5,6).stream().map(number->number+"").filter(word->word.startsWith("1")).forEach(System.out::println);
//		System.out.println(Arrays.asList().stream().findFirst());
//		System.out.println(Arrays.asList(1).stream().findFirst().orElse(100));
//		System.out.println(Arrays.asList().stream().findFirst().orElseGet(()->1));
//		System.out.println(Arrays.asList().stream().findFirst().orElseThrow(()->new Exception("Hello")));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toList()));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toCollection(ArrayList::new)));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toSet()));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));
//		System.out.println(Arrays.asList(1,2,3,4,5,6).stream().collect(Collectors.toMap(a->a,a->a*a)));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toSet()).stream().sorted(Collections.reverseOrder()).findFirst());
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toCollection(ArrayList::new)));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toCollection(LinkedList::new)));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toCollection(HashSet::new)));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.toCollection(LinkedHashSet::new)));
//		Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(a->System.out.println(a.getKey()+" "+a.getValue()));
//		Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().sorted((Map.Entry.comparingByValue())).forEach(a->System.out.println(a.getKey()));
//		Set<Integer> set=new HashSet<Integer>();
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().filter(a->!set.add(a)).collect(Collectors.toSet()));
//		System.out.println(Arrays.asList(1,2,3,4,5,2,3,42,2,1,2,3,4,4,2).stream().filter(a->!set.add(a)).collect(Collectors.toCollection(LinkedHashSet::new)));
//		System.out.println(Arrays.asList(8,262,62,82,34,56,1,2,34,8).stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()));
		System.out.println(Arrays.stream("supriya".split("")).reduce("",(a,b)->b+a));
//		System.out.println(Arrays.stream(new int[] {1,2,3}).filter(n->n%2!=0).count());
//		System.out.println(Arrays.stream("hiii".split("")).reduce("",(a,b)->b+a));
//		System.out.println(Arrays.asList(1,2,3,4,5,6).stream().mapToInt(i->i).summaryStatistics().getMin());
		System.out.println(Arrays.asList("String", "hel", "OKOK","okok", "NULLGgfgty").stream()
				.sorted(Collections.reverseOrder(Comparator.comparing(String::length))
				.thenComparing((str1, str2) -> str1.compareToIgnoreCase(str2))).collect(Collectors.toList()));
//		System.out.println(Arrays.asList(1,2,3,4,5,5,6,6).stream().filter(num->Collections.frequency(Arrays.asList(1,2,3,4,5,5,6,6),num)==1).collect(Collectors.toList()));
//		System.out.println( Stream.of(Arrays.asList(1,2,3),Arrays.asList(4,5,6,7),Arrays.asList(8,9)).flatMap(list->list.stream()).collect(Collectors.toList()));
//		Map<Integer,String> map=new HashMap<>();
//		map.put(1,"Sup");
//		map.put(2, "pavan");
//		map.put(3, "hello");
//		map.put(4 , "ok");
//		map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(m->System.out.println(m.getKey()+" "+m.getValue()));
//		Set<Integer> set=map.keySet();
//		Iterator itr=set.iterator();
//		while(itr.hasNext()) {
//			System.out.println(map.get(itr.next()));
//		}
//		for (Map.Entry<Integer, String> m : map.entrySet()) {
//			System.out.println(m.getKey());
//		}
//		System.out.println(	Arrays.asList("supriya".split("")).stream().reduce("",(a,b)->(b+a)));
//		System.out.println(	Arrays.asList("hi","hello","hi").stream().collect(Collectors.toSet()).stream().collect(Collectors.toMap(Function.identity(),a->Collections.frequency(Arrays.asList("hi","hello","hi"),a))));
		TreeSet<Integer> ts=new TreeSet<>();
//		ts.add(1);
//		ts.add(null);
//		System.out.println(ts);
		
//		
//	    String nullName = null;
//	    String name = Optional.ofNullable(nullName).get();
		int min = 10, max = 50;
        long seed = System.nanoTime();
        int randomNum = min + (int) ((System.nanoTime()) % (max - min + 1));
        System.out.println("Random Number: " + System.nanoTime()+" "+randomNum);
//
	
	}
	
//    /**
//     * You receive a list of Books as input parameter. The Book stores its Covers in a list. The Covers store its height
//     * and width as primitive integers. Each Book can have different number of Covers and each Cover can be different in
//     * size. Please return a List that has the Book objects that has Covers that's area is over a given input threshold.
//     * The id of the Cover and Book is unique in the entire context. You can have a look how the classes look like
//     * below.
//     *
      public record Book(int id, List<Cover> covers) {}
      public record Cover(int id, int width, int height) implements Comparable<Cover> {
          public int getArea() {
              return width * height;
          }
     
          @Override
          public int compareTo(Cover cover) {
              if (this.getArea() == cover.getArea()) {
                  return 0;
              }
              return this.getArea() > cover.getArea() ? 1 : -1;
          }
      }
     
     
    private static List<Book> filterBooksWithCoverAreaOverTheThreshold(List<Book> books, int threshold) {
    	Function<List<Cover>,Integer> filterThreshold=(covers)->{
    		return (int) covers.stream().filter(cover->cover.getArea()>=threshold).toList().size();
    	};
		return books.stream().filter(book->filterThreshold.apply(book.covers())>=threshold).toList();
    }
}
