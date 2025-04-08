package com.epam;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

class Tree {
	int data;
	Tree left;
	Tree right;

	public Tree(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

public class BinaryTree {
	Tree root;

	public BinaryTree() {
		root = null;
	}

	public void insert(int key) {
		root = insertRec(root, key);
	}

	public Tree insertRec(Tree root, int key) {
		if (root == null) {
			root = new Tree(key);
			return root;
		}
		if (key < root.data)
			root.left = insertRec(root.left, key);
		else if (key > root.data)
			root.right = insertRec(root.right, key);
		return root;
	}

	public void inorder() {
		inorderRec(root);
	}

	// A utility function to do inorder traversal of BST
	public void inorderRec(Tree root2) {
		if (root2 != null) {
			inorderRec(root2.left);
			System.out.print(root2.data + " ");
			inorderRec(root2.right);
		}
	}

	public static boolean pow(int n) {
		float temp = n;
		while (temp > 1) {
			temp = (float) temp / 10;
		}
		return temp == 1.0;
	}

	public static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return BinaryTree.gcd(b % a, a);
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.insert(50);
		tree.insert(30);
		tree.insert(20);
		tree.insert(40);
		tree.insert(70);
		tree.insert(60);
		tree.insert(80);

//        tree.inorder();
//        int num1=2;
//        int den1=3;
//        int num2=1;
//        int den2=2;
//        int den3;
//        den3=BinaryTree.gcd(den1, den2);
//        den3=(den1*den2)/den3;
//        int num3;
//        num3=num1*(den3/den1)+num2*(den3/den2);
//        int cf=gcd(num3,den3);
//        System.out.println(num3/cf+"/"+den3/cf);

//        int n=12;
//        while(n%2==0) {
//        	System.out.println(2+" ");
//        	n=n/2;
//        }
//        for(int i=3;i<=Math.sqrt(n);i+=2) {
//        	while(n%i==0) {
//        		System.out.println(i+" ");
//        		n=n/i;
//        	}
//        }
//        if(n>2) {
//        	System.out.println(n);
//        }
//      System.out.println((double)(int)(Math.sqrt(2)*1e5)/1e5); 

//        String s="xxyyzz";
//        Map<String,Long> map=Arrays.asList(s.split("")).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//        for(int i=0;i<s.length();i++) {
//        	if(map.get(Character.toString(s.charAt(i)))==1) {
//        		System.out.println(s.charAt(i));
//        		break;
//        	}
//        }
		
		
//		String[] str = new String[] { "cat", "dog", "god" };
//		Map<String, List<String>> map = new HashMap<>();
//		for (String s : str) {
//			char[] chars = s.toCharArray();
//			Arrays.sort(chars);
//			String key = new String(chars);
//			if (!map.containsKey(key)) {
//				map.put(key, new ArrayList<>());
//			}
//			map.get(key).add(s);
//		}
//		System.out.println(map.values());
		
		
//		String[] s="aabbbbbCdAA".split("");
//		int count=s.length>0?1:0;
//		int start=0;
//		int end=0;
//		int maxi=Integer.MIN_VALUE;
//		for(int i=1;i<s.length;i++) {
//			if(s[i].equals(s[i-1])) {
//				count+=1;
//			}else {
//				count=1;
//			}
//			if(count>maxi) {
//				maxi=count;
//				end=i;
//			}
//			
//		}
//		System.out.println(end-maxi+1+" "+end+" "+String.join("", s).substring(end-maxi+1, end+1));
		
		
//		String[] s="aaabbbaad".split("");
//		int count=s.length>0?1:0;
//		String ans=s[0];
//		for(int i=1;i<s.length;i++) {
//			if(s[i].equals(s[i-1])) {
//				count+=1;
//			}else {
//				ans+=count;
//				ans+=s[i];
//				count=1;
//			}
//		}
//		ans+=count;
//		System.out.println(ans);
		
//		String s="The slow purple oryx meanders past the quiescent canine";
//		String result = String.join("",Arrays.asList(s.replaceAll("[^a-zA-Z]", "").toLowerCase().split("")).stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)));
//		String alphabets="abcdefghijklmnopqrstuvwxyz";
//		String ans="";
//		for(int i=0;i<alphabets.length();i++) {
//			if(!result.contains(String.valueOf(alphabets.charAt(i)))) {
//				ans+=alphabets.charAt(i);
//			}
//		}
//		System.out.println(ans);
		
		
		String s="supriya is a good employee";
		System.out.println(s.contains(s.charAt(0)+""));
		
		System.out.println(Arrays.asList("supriya".split("")).stream().reduce("",(a,b)->b+a));
	}
}
