package com.epam;


import java.util.*;

public class Solution {
   public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean[] visited = new boolean[wordList.size()];
        LinkedList<Integer> queue =new LinkedList<>();
        for (int i=0;i<wordList.size();i++){
            if (wordList.get(i).equals(endWord)){
                queue.add(i);
                visited[i]=true;
                break;
            }
        }
        int counter =1;      
        while (!queue.isEmpty()){
            int toPop=queue.size();
            for (int i=0;i<toPop;i++){
                int cur= queue.pop();
                if (isSingleLtrDiff(wordList.get(cur),beginWord)) return counter+1;        
                for (int j=0;j<wordList.size();j++){
                  if (!visited[j]&&isSingleLtrDiff(wordList.get(cur),wordList.get(j))){
                        queue.add(j);
                        visited[j]=true;
                    }
                }
            }
            counter++;
        }
        return 0;    
    }

    private boolean isSingleLtrDiff(String s1, String s2){
        int counter =0; 
        for (int i=0;i<s1.length();i++){
            if (s1.charAt(i)!=s2.charAt(i)) counter++;
        }
        return (counter==1);
    }
}



