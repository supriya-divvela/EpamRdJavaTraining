package com.epam.rd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringReverse {
	void StringReverse(){
		System.out.println("hii");
	}
	public static String reverseString(String word) {
		if(word==null || word.length()==0) {
			return "";
		}
		StringBuilder string=new StringBuilder(word);
		return string.reverse().toString();
	}
	public static Map<Integer,String> sortHashMapByValues(){
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "Java");
		map.put(2, "Python");
		map.put(3, "c++");
		map.put(4, ".net");
		return map.entrySet().stream().sorted((e1,e2)->e1.getValue().compareTo(e2.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));	
	}
	
	public static void findAllPermutation(String word,int index,StringBuffer buffer,int length){
		if(index==length) {
			System.out.println(buffer);
			return;
		}
		
		for(int i=index;i<word.length();i++) {
			buffer.append(word.charAt(i));
//			System.out.println(buffer+" before");
			findAllPermutation(word, i+1, buffer, word.length());
			buffer.deleteCharAt(buffer.length()-1);
//			findAllPermutation(word,i,buffer.deleteCharAt(buffer.length()-1),word.length());
//			System.out.println(buffer+" after");
		}
	}
	public static void main(String[] args) {
		StringReverse s=new StringReverse();
		System.out.println(reverseString("Hii"));
		System.out.println(reverseString(null));
		sortHashMapByValues();
		findAllPermutation("abc", 0, new StringBuffer(), 3);
	}
}
