package com.epam;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Utils {

    // Generic types should be replaced with appropriate types for method type and method parameter types. (String[], String, int, etc.).

    public static long sumOfPrimes(int n) {
    	if (n < 0) {
            throw new IllegalArgumentException("Input cannot be negative.");
        }
        return LongStream.rangeClosed(2, n)
                .filter(i->isPrime(i))
                .sum();
    }

    private static boolean isPrime(long i) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(i))
                .allMatch(n -> i % n != 0);
    }


    /**
    * @see <a href="https://en.wikipedia.org/wiki/Title_case">Title_case</a>
    */
    public static String convertToTitleCase(String input) {
    	String[] words = input.split("\\s"); 
    	  
        // StringBuilder to store the result 
        StringBuilder result = new StringBuilder(); 
  
        // iterate through each word 
        for (String word : words) { 
            // capitalize the first letter, append the rest of the word, and add a space 
            result.append(Character.toTitleCase(word.charAt(0))) 
                  .append(word.substring(1)) 
                  .append(" "); 
        } 
  
        // convert StringBuilder to String and trim leading/trailing spaces 
        return result.toString().trim(); 
    }

   

    public static <T, U> T groupAnagrams(U input)  { 
        if (input instanceof List<?>) {
            List<String> strList = (List<String>) input;
            Map<String, List<String>> map = new HashMap<>(); 
            for (String str : strList) { 
                char[] arr = new char[26]; 
                for (int i = 0; i < str.length(); i++) { 
                    arr[str.charAt(i) - 'a']++; 
                } 
                String ns = new String(arr); 
      
                map.computeIfAbsent(ns, k -> new ArrayList<>()).add(str); 
            } 
            return (T) new ArrayList<>(map.values());
        } else {
            throw new IllegalArgumentException("Input needs to be of type List<String>");
        }
    } 
    
    public static void main(String[] args) {
		System.out.println(sumOfPrimes(5));
	}
}
