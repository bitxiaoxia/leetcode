//package com.bitxiaoxia;
//
//
//import com.bitxiaoxia.leetcode.utils.PrintUtils;
//
//import java.util.*;
//import java.util.concurrent.*;
//
//public class Main {
//	public static volatile boolean flag = false;
//
//	public static void main(String[] args) throws Exception{
////		List<List<Integer>> resultList = combinationSum3(3,15);
////		for(List<Integer> list:resultList){
////			PrintUtils.print(list);
////		}
////		System.out.println(resultList);
//
//	}
//
//	public static List<List<Integer>> threeSum(int[] nums) {
//		Arrays.sort(nums);
//		int length = nums.length;
//		List<List<Integer>> resultList = new ArrayList<>();
//		Deque<Character> deque = new ArrayDeque<>();
//		deque.pop();
//		for(int i = 0;i<length;i++){
//			int firstNum = nums[i];
//			if(i>=1 && nums[i]==nums[i-1]){
//				continue;
//			}
//			int target = -firstNum;
//			StringBuilder builder = new StringBuilder();
//			builder.setLength(0);
//			int left = i+1;int right = length-1;
//			while(left<right){
//				if(nums[left]+nums[right] == target){
//					List<Integer> tempList = new ArrayList<>();
//					tempList.add(firstNum);
//					tempList.add(nums[left]);
//					tempList.add(nums[right]);
//					resultList.add(tempList);
//					left++;
//					right--;
//				}else if(nums[left]+nums[right] < target){
//					while(nums[left] == nums[left+1]){
//						left++;
//					}
//					left++;
//				}else{
//					while(nums[right] == nums[right-1]){
//						right--;
//					}
//					right--;
//				}
//			}
//		}
//		return resultList;
//	}
//
//	public static boolean isPalindrome(int x) {
//		if(x<0){
//			Charac
//			return false;
//		}else{
//			Deque<Integer> deque = new ArrayDeque<>();
//			while(x>10){
//				deque.add(x%10);
//				x = x/10;
//			}
//			deque.add(x);
//
//			while(deque.size()>1){
//				int head = deque.pollFirst();
//				int tail = deque.pollLast();
//				if(head!=tail){
//					return false;
//				}
//			}
//			return true;
//		}
//	}
//	public static List<List<Integer>> combinationSum3(int k, int n) {
//		List<List<Integer>> resultList = new ArrayList<>();
//		List<Integer> path = new ArrayList<>();
//		backTrace(resultList,path,k,n,9);
//		return resultList;
//	}
//
//	private static void backTrace(List<List<Integer>> resultList,List<Integer> path,int k,int n,int curr){
//		if(curr<1||n<0){
//			return;
//		}
//		if(k==0&&n==0){
//			List<Integer> temp = new ArrayList<>();
//			temp.addAll(path);
//			resultList.add(temp);
//			return;
//		}
//		backTrace(resultList,path,k,n,curr-1);
//		if(n>=curr){
//			path.add(curr);
//			backTrace(resultList,path,k-1,n-curr,curr-1);
//			path.remove(path.size()-1);
//		}
//
//	}
//
//	public static String longestPalindrome(String s) {
//		char[] chars = s.toCharArray();
//		if(chars.length == 0){
//			return "";
//		}
//		char[] arr = new char[2*chars.length+1];
//		arr[0] = '|';
//		for(int i=0;i<chars.length;i++){
//			arr[2*i+1] = chars[i];;
//			arr[2*i+2] = '|';
//		}
//		String a= "abac";
//		int max = 0;
//		int pl = 0;
//		int pr = 0;
//		for(int i=0;i<arr.length;i++){
//			int left=i;
//			int right=i;
//			left--;
//			right++;
//			while(left>=0 && right<arr.length&&arr[left]==arr[right]){
//				if(right-left>max){
//					max = right-left;
//					pl = left;
//					pr = right;
//				}
//				left--;
//				right++;
//			}
//		}
//		System.out.println(String.format("%s %s %s",max,pl,pr));
//		StringBuilder builder = new StringBuilder();
//		for(int pos = pl;pos<=pr;pos++){
//			if(arr[pos]!='|'){
//				builder.append(arr[pos]);
//			}
//		}
//		return builder.toString();
//	}
//
//	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//		List<List<Integer>> resultList = new ArrayList<>();
//		List<Integer> path = new ArrayList<>();
//		Arrays.sort(candidates);
//		backTrace(resultList,path,candidates,target,candidates.length-1);
//		return resultList;
//	}
//
//	private static void backTrace(List<List<Integer>> resultList,List<Integer> path,int[] candidates,int target,int pos){
//		System.out.println(String.format("t:%s p:%s path: %s",target,pos,PrintUtils.getPrintStr(path)));
//
//		if(target==0){
//			List<Integer> temp = new ArrayList<>();
//			temp.addAll(path);
//			resultList.add(temp);
//			return;
//		}
//		if(target<0 || pos<=0){
//			return;
//		}
//
//		int tempPos = pos;
//		int curr = candidates[pos];
//		for(;tempPos>=0 && candidates[tempPos]==curr;tempPos--){
//
//		}
//
//		backTrace(resultList,path,candidates,target,tempPos);//跳过当前数字
//		for(int i = 1;tempPos+i<=pos;i++){
//			path.add(curr);
//			backTrace(resultList,path,candidates,target-i*curr,tempPos);
//		}
//		for(int i =1;tempPos+i<=pos;i++){
//			path.remove(path.size()-1);
//		}
//	}
//
//	static final int tableSizeFor(int cap) {
//		int n = cap - 1;
//		n |= n >>> 1;
//		n |= n >>> 2;
//		n |= n >>> 4;
//		n |= n >>> 8;
//		n |= n >>> 16;
//		return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
//	}
//
//	private static void change(Person p){
//		p = new Person(12,"change");
//	}
//
//	private static class Person{
//		int age;
//		String name;
//
//		public Person(int age, String name) {
//			this.age = age;
//			this.name = name;
//		}
//
//		public int getAge() {
//			return age;
//		}
//
//		public void setAge(int age) {
//			this.age = age;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//	}
//}
