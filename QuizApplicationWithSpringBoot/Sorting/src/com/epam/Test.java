package com.epam;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Test {
	public static int longestSubstring(String s, int k) {
		int[] frequency = new int[26];
		for (int i = 0; i < s.length(); i++) {
			frequency[(s.charAt(i) - 'a')]++;
		}
		boolean valid = true;
		int start = 0;
		int maxLength = 0;
		for (int end = 0; end < s.length(); end++) {
			if (frequency[(s.charAt(end) - 'a')] > 0 && frequency[(s.charAt(end) - 'a')] < k) {
				String subString = s.substring(start, end);
				maxLength = Math.max(maxLength, longestSubstring(subString, k));
				start = end + 1;
				valid = false;
			}

		}
		if (valid) {
			return s.length();
		} else {
			return Math.max(longestSubstring(s.substring(start), k), maxLength);
		}

	}

	public static String removeDuplicateLetters(String s) {
		s = s.trim();
		if (s.length() == 0) {
			return "";
		}

		int[] lastIndex = new int[26];
		for (int i = 0; i < s.length(); i++) {
			lastIndex[s.charAt(i) - 'a'] = i;
		}
		boolean[] seen = new boolean[26];
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			if (seen[c]) {
				continue;
			}
			while (!deque.isEmpty() && deque.peek() > c && i < lastIndex[deque.peek()]) {
				seen[deque.pop()] = false;
			}
			deque.push(c);
			seen[c] = true;
		}
		return new StringBuilder(deque.stream().map(i -> (char) (i + 'a')).collect(Collector.of(StringBuilder::new,
				StringBuilder::append, StringBuilder::append, StringBuilder::toString))).reverse().toString();
	}


	public static Student[] sortStudentsByGradeAndId(Student[] students) {
		Arrays.sort(students, (student1, student2) -> {
			if (student1.getGrade().compareTo(student2.getGrade()) > 0) {
				return 1;
			} else if (student1.getGrade().compareTo(student2.getGrade()) < 0) {
				return -1;
			} else {
				if (student1.getId().compareTo(student2.getId()) > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return students;

	}

	public static int minCost(int[][] costs) {
		int n = costs.length - 1;
		int[][] dp = new int[costs.length][3];
		dp[0][0] = costs[0][0];
		dp[0][1] = costs[0][1];
		dp[0][2] = costs[0][2];
		for (int i = 1; i < costs.length; i++) {
			dp[i][0] = Math.min(costs[i][0] + dp[i - 1][1], dp[i - 1][2] + costs[i][0]);
			dp[i][1] = Math.min(costs[i][1] + dp[i - 1][0], dp[i - 1][2] + costs[i][1]);
			dp[i][2] = Math.min(costs[i][2] + dp[i - 1][0], dp[i - 1][1] + costs[i][2]);
		}
		return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]);
	}

	public static int findLength(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
			return 0;
		}
		int max = 0;
		int[][] dp = new int[nums1.length + 1][nums2.length + 1];
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp.length; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}

	public static int numRollsToTarget(int n, int k, int target) {
		int[][] dp = new int[n + 1][target + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= target; j++) {
				int ans = 0;
				for (int f = 1; f <= k; f++) {
					if (j - f >= 0) {
						ans = (ans%1000000007 + dp[i - 1][j - f]%1000000007)%1000000007;
					}
				}
				dp[i][j] = ans;

			}
		}
		return dp[n][target];
	}
//  int[][] a={ { 5,10 }, { 15,20},{ 0,30}};
	public static int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return -1;
		}
		intervals = Arrays.stream(intervals).sorted((a1, a2) -> a1[1] - a2[1]).toArray(int[][]::new);
		int finish = intervals[0][1];
		int count = 1;
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] > finish) {
				finish = intervals[i][1];
				count++;
			}
		}
		return count;
	}


	public static void main(String[] args) {
//		System.out.println(numRollsToTarget(30,30,500));
//		int[][] cost= {{7,6,2}};
//		System.out.println(minCost(cost));
//		int[] nums1 = {1,2,3,2,1}, nums2 = {3,2,1,4,7};
//		System.out.println(findLength(nums1, nums2));
//		int[] input= null;
//		 System.out.println(Arrays.stream(input).map(i->i).sum());
//		IntStream.range(0,13).forEach((i)->IntStream.range(0,13).filter((j)->i==j || j==13-i+1).forEach(j->System.out.println("#"))));
//		int n=5;
//		for(int i=0;i<2*n-1;i++) {
//			for(int j=0;j<2*n-1;j++) {
//				if(i==j || j==2*n-1-i-1) {
//					System.out.print("#");
//				}
//				else {
//					System.out.print(" ");
//				}
//			}
//			System.out.println();
//		}
		/////////////////////////////////////////////////////
//		String str = "abcd abcdefg abcdef";
//		 if(str.equals(null) || str.equals("")){
//	            System.out.println("null");
//	        }
//	        String longestWord="";
//	        String[] word=str.split(" ");
//	        for(int i=0;i<word.length;i++){
//	            if(word[i].length()>longestWord.length()){
//	                longestWord=word[i];
//	            }
//	        }
//		System.out.println(longestWord.length() != 0 ? longestWord : null);
		///////////////////////////////////////////////////////
//		Student[] students= {new Student("1","a"),new Student("2","b")};
//		System.out.println(Test.sortStudentsByGradeAndId(students));
//		System.out.println(Arrays.stream(students).sorted(Comparator.comparing(Student::getGrade).thenComparing(Student::getId)).toList().toArray());
//		String line=""
//		if(line==null || line.equals("")){
//           System.out.println(null);
//        }
//        String str =line.toLowerCase();
//        String longestWord="";
//        int maxVowelCount=0;
//        String[] word=str.split(" ");
//        for(int i=0;i<word.length;i++){
//            int countOfVowels=0;
//           for(int j=0;j<word[i].length();j++){
//                if((word[i].charAt(j)+"").equals("a") || (word[i].charAt(j)+"").equals("e") || (word[i].charAt(j)+"").equals("i") || (word[i].charAt(j)+"").equals("o") || (word[i].charAt(j)+"").equals("u")){
//                    countOfVowels++;
//                }
//           }
//           if(maxVowelCount<countOfVowels){
//                maxVowelCount=countOfVowels;
//                longestWord=word[i];
//           }
//        }
//        return longestWord.length()!=0 ? longestWord : null ;
//		String s1=new String("hello");
//		String s2=new String("hello");
//		System.out.println(s1==s2);
//		String str=null;
//		System.out.println(str.toString());
//		Object obj=new Integer(3);
////		System.out.println(obj.toString());
//		System.out.println(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3))
//				.stream().flatMap(list -> list.stream()).mapToInt(x -> x).summaryStatistics());
//		String str=null;
//		System.out.println(str.valueOf(10));
//		int[] nums = { 1, 2, 3, 4, 5, 6 };
//		int k = 3;
//		if (Objects.isNull(nums) || nums.length == 0) {
//			throw new IllegalArgumentException();
//		}
//		Arrays.sort(nums);
//		System.out.println(nums[nums.length - k]);
//		int[][] a={ { 0,30}, { 5,10 }, { 15,20}};
//		int[][] a = { { 7, 10 }, { 2, 4 } };
//		int[][] a= {{1,2},{3,4},{0,6},{5,7},{8,9},{5,9}};
//		int[][] a={ { 0,30}, { 5,10 }, { 15,20},{7,12},{25,30}};
//		System.out.println(minMeetingRooms(a));
//		String s =null;
//		System.out.println(removeDuplicateLetters(s).toString());
//		String s = "aaabb";
//		System.out.println(longestSubstring(s, 3));
//		IntStream.range(1, 10).filter(i->{
//			System.out.println("1");
//			return i%2==0;
//		}).filter(i->{
//			System.out.println("0");
//			return i>3;
//		}).limit(1).forEach(System.out::println);
//		Optional num=Stream.of(5,10,15).filter(n->n%5==0).findAny();
//		System.out.println(num);
//		System.out.println(Stream.of(2,4).filter(i->i%2!=0).allMatch(i->i%2!=0));
//		System.out.println(Stream.of(2,4).filter(i->i%2!=0).anyMatch(i->i%2!=0));
//		Stream.builder().add("hello").add("ok").add("hello").build().distinct().forEach(System.out::println);
//		System.out.println(Stream.empty());
		Stream.of(2,4).filter(i->i%2==0).limit(0).forEach(System.out::println);
		System.out.println(Stream.of(2,4,6,8).filter(i->i%2==0).limit(5).max((i1,i2)->i1.compareTo(i2)));
		Stream.of(new Customer(1,"whello"),new Customer(2,"ok")).sorted().forEach(System.out::println);
	}

}
