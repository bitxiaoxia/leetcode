package com.bitxiaoxia;


import com.bitxiaoxia.leetcode.ThroneInheritance;
import com.bitxiaoxia.leetcode.utils.PrintUtils;

import java.util.*;
import java.util.concurrent.*;

public class Main {
	public static volatile boolean flag = false;
	int maxNum;
	public int maximumRequests(int n, int[][] requests) {
		ExecutorService service = Executors.newFixedThreadPool(1);
		int[] eNums = new int[n];
		maxNum =0;
		backTrace(eNums,requests,0,0);
		return maxNum;
	}

	public void backTrace(int[] eNums,int[][] requests,int pos,int satisfied){

		boolean flag =true;
		for(int e:eNums){
			if(e != 0){
				flag = false;
				break;
			}
		}

		if (flag){
			if (satisfied>maxNum){
				maxNum = satisfied;
			}
		}
		if(pos == requests.length){
			return;
		}
		backTrace(eNums,requests,pos+1,satisfied);

		int[] tt = requests[pos];

		eNums[tt[0]] --;
		eNums[tt[1]] ++;
		backTrace(eNums,requests,pos+1,satisfied+1);
		eNums[tt[0]] ++;
		eNums[tt[1]] --;
	}
	public static void main(String[] args) throws Exception{
		Main main = new Main();
//		int[][] req = {{0,1},{1,0},{0,1},{1,2},{2,0},{3,4}};
		int[][] req = {{0,0},{1,2},{2,1}};
		System.out.println(main.maximumRequests(3,req));

//		List<List<Integer>> resultList = combinationSum3(3,15);
//		for(List<Integer> list:resultList){
//			PrintUtils.print(list);
//		}
//		System.out.println(resultList);
//		String[] logs = {"d1/","d2/","../","d21/","./"};
//		System.out.println(minOperations(logs));
//		int[] cus = {10,10,6,4,7};
//		System.out.println(minOperationsMaxProfit(cus,3,8));
//		ThroneInheritance inheritance = new ThroneInheritance("king");
//		inheritance.birth("king","andy");
//		inheritance.birth("king","bob");
//		inheritance.birth("king","catherine");
//		inheritance.birth("andy","matthew");
//		inheritance.birth("bob","alex");
//		inheritance.birth("bob","asha");
////		inheritance.death("asha");
//		PrintUtils.print(inheritance.getInheritanceOrder());
//		inheritance.death("bob");
//		PrintUtils.print(inheritance.getInheritanceOrder());
	}

	public static int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
		int maxValue = 0;//最大利润
		int maxTimes = -1;//最大利润对应的轮次
		if(runningCost>=4*boardingCost){
			return -1;
		}
		int leftCustomers = 0;
		int currValue = 0;
		for(int i=0;i<customers.length;i++){
			int custThisTime = leftCustomers+customers[i];

			currValue -=runningCost;
			if (custThisTime >=4){
				//满载
				currValue += 4*boardingCost;
				leftCustomers = custThisTime-4;
			}else{
				//非满载
				currValue += custThisTime*boardingCost;
				leftCustomers = 0;
			}

			if(currValue > maxValue){
				maxValue = currValue;
				maxTimes = i+1;
			}
		}
		while(leftCustomers > runningCost/boardingCost){
			//剩余人员，如果转一次亏了，不转了
			maxTimes++;
			leftCustomers -=4;
		}

		return maxTimes;
	}

	public static int minOperations(String[] logs) {
		int cnt = 0;
		for(String path:logs){
			if ("../".equals(path)){
				cnt--;
			}else if("./".equals(path)){

			}else{
				cnt++;
			}
			if(cnt<0){
				cnt = 0;
			}
		}
		return cnt;
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		int length = nums.length;
		List<List<Integer>> resultList = new ArrayList<>();
		Deque<Character> deque = new ArrayDeque<>();
		deque.pop();
		for(int i = 0;i<length;i++){
			int firstNum = nums[i];
			if(i>=1 && nums[i]==nums[i-1]){
				continue;
			}
			int target = -firstNum;
			StringBuilder builder = new StringBuilder();
			builder.setLength(0);
			int left = i+1;int right = length-1;
			while(left<right){
				if(nums[left]+nums[right] == target){
					List<Integer> tempList = new ArrayList<>();
					tempList.add(firstNum);
					tempList.add(nums[left]);
					tempList.add(nums[right]);
					resultList.add(tempList);
					left++;
					right--;
				}else if(nums[left]+nums[right] < target){
					while(nums[left] == nums[left+1]){
						left++;
					}
					left++;
				}else{
					while(nums[right] == nums[right-1]){
						right--;
					}
					right--;
				}
			}
		}
		return resultList;
	}

	public static boolean isPalindrome(int x) {
		if(x<0){
			return false;
		}else{
			Deque<Integer> deque = new ArrayDeque<>();
			while(x>10){
				deque.add(x%10);
				x = x/10;
			}
			deque.add(x);

			while(deque.size()>1){
				int head = deque.pollFirst();
				int tail = deque.pollLast();
				if(head!=tail){
					return false;
				}
			}
			return true;
		}
	}
	public static List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		backTrace(resultList,path,k,n,9);
		return resultList;
	}

	private static void backTrace(List<List<Integer>> resultList,List<Integer> path,int k,int n,int curr){
		if(curr<1||n<0){
			return;
		}
		if(k==0&&n==0){
			List<Integer> temp = new ArrayList<>();
			temp.addAll(path);
			resultList.add(temp);
			return;
		}
		backTrace(resultList,path,k,n,curr-1);
		if(n>=curr){
			path.add(curr);
			backTrace(resultList,path,k-1,n-curr,curr-1);
			path.remove(path.size()-1);
		}

	}

	public static String longestPalindrome(String s) {
		char[] chars = s.toCharArray();
		if(chars.length == 0){
			return "";
		}
		char[] arr = new char[2*chars.length+1];
		arr[0] = '|';
		for(int i=0;i<chars.length;i++){
			arr[2*i+1] = chars[i];;
			arr[2*i+2] = '|';
		}
		String a= "abac";
		int max = 0;
		int pl = 0;
		int pr = 0;
		for(int i=0;i<arr.length;i++){
			int left=i;
			int right=i;
			left--;
			right++;
			while(left>=0 && right<arr.length&&arr[left]==arr[right]){
				if(right-left>max){
					max = right-left;
					pl = left;
					pr = right;
				}
				left--;
				right++;
			}
		}
		System.out.println(String.format("%s %s %s",max,pl,pr));
		StringBuilder builder = new StringBuilder();
		for(int pos = pl;pos<=pr;pos++){
			if(arr[pos]!='|'){
				builder.append(arr[pos]);
			}
		}
		return builder.toString();
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		Arrays.sort(candidates);
		backTrace(resultList,path,candidates,target,candidates.length-1);
		return resultList;
	}

	private static void backTrace(List<List<Integer>> resultList,List<Integer> path,int[] candidates,int target,int pos){
		System.out.println(String.format("t:%s p:%s path: %s",target,pos,PrintUtils.getPrintStr(path)));

		if(target==0){
			List<Integer> temp = new ArrayList<>();
			temp.addAll(path);
			resultList.add(temp);
			return;
		}
		if(target<0 || pos<=0){
			return;
		}

		int tempPos = pos;
		int curr = candidates[pos];
		for(;tempPos>=0 && candidates[tempPos]==curr;tempPos--){

		}

		backTrace(resultList,path,candidates,target,tempPos);//跳过当前数字
		for(int i = 1;tempPos+i<=pos;i++){
			path.add(curr);
			backTrace(resultList,path,candidates,target-i*curr,tempPos);
		}
		for(int i =1;tempPos+i<=pos;i++){
			path.remove(path.size()-1);
		}
	}

	static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
	}

	private static void change(Person p){
		p = new Person(12,"change");
	}

	private static class Person{
		int age;
		String name;

		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
