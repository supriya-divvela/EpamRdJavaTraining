package com.epam.staticdemo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Part5 {
	public static int findLongestSubstringWithoutRepeatingCharacters(final String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		int res = 0;
		Deque<Character> deque = new ArrayDeque<>();
		for (int i = 0; i < input.length(); i++) {
			while (deque.contains(input.charAt(i)) && !deque.isEmpty()) {
				deque.pop();
			}
			if (!deque.contains(input.charAt(i))) {
				deque.add(input.charAt(i));
			}
			res = Math.max(res, deque.size());
		}
		return res;
	}

	public static int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null) {
			return null;
		}
		if (nums.length == 0) {
			return new int[0];
		}
		Deque<Integer> list=new ArrayDeque<Integer>();
		Deque<Integer> maxList=new ArrayDeque<Integer>();
		for(int i=0;i<nums.length;i++) {
			if(!list.isEmpty() && i-k==list.getFirst()) {
				list.pop();
			}
			while(!list.isEmpty() && nums[list.getLast()]<=nums[i]) {
				list.pop();
			}
			list.add(i);
			if(i>=k-1) {
				maxList.add(nums[list.getFirst()]);
			}
		}
		return maxList.stream().mapToInt(i->i).toArray();
	}

	public static void main(String[] args) {
//		String s = "abcabcbb";
//		System.out.println(findLongestSubstringWithoutRepeatingCharacters(s));
		int[] nums = {1};
		int k = 1;
		System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
	}
}
