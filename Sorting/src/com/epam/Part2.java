package com.epam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2 {
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
						ans = (ans % 1000000007 + dp[i - 1][j - f] % 1000000007) % 1000000007;
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
		int[] startTime = Arrays.stream(intervals).map(i -> Arrays.stream(i).findFirst().getAsInt()).sorted()
				.mapToInt(i -> i).toArray();
		int[] endTime = Arrays.stream(intervals).map(i -> Arrays.stream(i).map(p -> p).skip(1).findFirst().getAsInt())
				.sorted().mapToInt(r -> r).toArray();
		Arrays.stream(startTime).forEach(System.out::println);
		Arrays.stream(endTime).forEach(System.out::println);
		int start = 0;
		int end = 0;
		int result = 0;
		int count = 0;
		while (start < intervals.length) {
			if (startTime[start] < endTime[end]) {
				start++;
				count++;
			} else {
				end++;
				count--;
			}
			result = Math.max(result, count);

		}
		return count;
	}

	public static String constructSmallestNumberFromDIString(String input) {
		if (input == null || input.length() == 0) {
			return "";
		}
		int[] a = new int[input.length() + 1];
		for (int i = 0; i < input.length() + 1; i++) {
			a[i] = i + 1;
		}
		int i = 0;
		while (i < input.length()) {
			int temp = i;
			while (temp < input.length() && input.charAt(temp) == 'D') {
				temp++;
			}
			int left = i;
			int right = temp;
			while (left < right) {
				int newTemp = a[left];
				a[left] = a[right];
				a[right] = newTemp;
				left++;
				right--;
			}
			if (temp != i) {
				i = temp - 1;
			}
			i++;
		}
		return Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(""));
	}

	public static int minProductSum(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
			return 0;
		}
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		return IntStream.range(0, nums1.length).map(i -> nums1[i] * nums2[nums1.length - i - 1]).sum();
	}

	public int[] printLinkedListInReverse(ImmutableListNode head) {
		if (head == null) {
			return null;
		}
		Deque<Integer> stack = new ArrayDeque<>();
		while (head != null) {
			stack.push(head.printValue());
			head = head.getNext();
		}
		return stack.stream().mapToInt(i -> stack.pop()).toArray();
	}

	public int heightOfTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(1 + heightOfTree(root.getLeft()), 1 + heightOfTree(root.getRight()));
	}

	public int findSum(TreeNode root, int index, int height) {
		if (root == null) {
			return 0;
		}
		if (index == height) {
			return root.getValue();
		}
		return findSum(root.getLeft(), index + 1, height) + findSum(root.getRight(), index + 1, height);
	}

	public int deepestLeavesSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return findSum(root, 1, heightOfTree(root));
	}

	public static int connectSticks(int[] sticks) {
		if (sticks == null || sticks.length <= 1) {
			return 0;
		}
		PriorityQueue<Integer> numbers = new PriorityQueue<>();
		for (int i = 0; i < sticks.length; i++) {
			numbers.add(sticks[i]);
		}
		int cost = 0;
		while (numbers.size() > 1) {
			int c = numbers.poll() + numbers.remove();
			cost += c;
			numbers.add(cost);
		}
		return cost;
	}

	public int[] deckRevealedIncreasing(int[] deck) {
		if (deck == null) {
			return new int[0];
		}
		int n = deck.length;
		Arrays.sort(deck);
		Queue<Integer> q = new LinkedList<>();
		for (int i = n - 1; i >= 0; --i) {
			if (!q.isEmpty())
				q.add(q.poll());
			q.add(deck[i]);
		}
		int[] res = new int[n];
		for (int i = n - 1; i >= 0; --i) {
			res[i] = q.poll();
		}
		return res;
	}

	private List<String> str = new ArrayList<>();
	private StringBuilder sb = new StringBuilder();
	private String[] arr = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public void help(int idx, String digits, List<String> str, String[] arr, int n, StringBuilder sb) {
		if (idx == n) {
			str.add(sb.toString());
			return;
		}
		int val = digits.charAt(idx) - '0';
		String s = arr[val];
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			help(idx + 1, digits, str, arr, n, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public List<String> letterCombinations(String digits) {
		if (digits == null || digits.length() == 0) {
			return str;
		}
		help(0, digits, str, arr, digits.length(), sb);
		return str;
	}

	public int countVowelPermutation(int n) {
		if (n == 0 || n < 0) {
			return 0;
		}
		long a = 1;
		long e = 1;
		long i = 1;
		long o = 1;
		long u = 1;
		int mod = 1000000007;
		long a2;
		long e2;
		long i2;
		long o2;
		long u2;
		for (int k = 2; k <= n; k++) {
			a2 = e % mod;
			e2 = (a % mod + i % mod) % mod;
			i2 = (a % mod + e % mod + o % mod + u % mod) % mod;
			o2 = (i % mod + u % mod) % mod;
			u2 = a % mod;
			a = a2;
			e = e2;
			i = i2;
			o = o2;
			u = u2;
		}
		return (int) ((a % mod + e % mod + i % mod + o % mod + u % mod) % mod);
	}
	public static void main(String[] args) {
//		int[] sticks = { 2, 4, 3 };
//		System.out.println(connectSticks(sticks));
//		int[] nums1 = {5,3,4,2}, nums2 = {4,2,2,5};
//		System.out.println(minProductSum(nums1, nums2));
//		Deque<Integer> stack = new ArrayDeque<>();
//		stack.push(1);
//		stack.push(2);
//		stack.push(3);
//		stack.push(4);
//		stack.stream().mapToInt(i -> stack.pop()).forEach(System.out::println);
//		System.out.println(constructSmallestNumberFromDIString("IIIDIDDD"));
//		int[][] a={ { 0,30}, { 5,10 }, { 15,20}};
//		int[][] a = { { 7, 10 }, { 2, 8 } };
//		int[][] a= {{1,2},{3,4},{0,6},{5,7},{8,9},{5,9}};
//		int[][] a={ { 0,30}, { 5,10 }, { 15,20},{7,12},{25,30}};
//		System.out.println(minMeetingRooms(a));
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
//		
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
//		Stream.of(2,4).filter(i->i%2==0).limit(0).forEach(System.out::println);
//		System.out.println(Stream.of(2,4,6,8).filter(i->i%2==0).limit(5).max((i1,i2)->i1.compareTo(i2)));
//		Stream.of(new Customer(1,"whello"),new Customer(2,"ok")).sorted().forEach(System.out::println);
	}
}
