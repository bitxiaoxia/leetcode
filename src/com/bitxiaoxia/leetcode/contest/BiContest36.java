package com.bitxiaoxia.leetcode.contest;

import com.bitxiaoxia.leetcode.utils.PrintUtils;

import java.util.*;

/**
 * Created by Bitxiaoxia on 2020/10/8.
 */
public class BiContest36 {
	public static void main(String[] args) {
		String a= "pvhmupgqeltozftlmfjjde";
		String b = "yjgpzbezspnnpszebzmhvp";
		System.out.println(checkPalindromeFormation(a,b));
	}

	public static boolean checkPalindromeFormation(String a, String b) {
		if(a.length()<=1){
			return true;
		}
		int n = a.length()-1;
		boolean[] flags = {true,true,true,true};//0: a自己回文，1，a,b回文，2，b自己回文，3，b,a回文
		int i =0;
		for(;i<(n+1)/2;i++){
			if (flags[0]){
				if(a.charAt(i) != a.charAt(n-i)){
					flags[0] = false;
				}
			}
			if(flags[1]){
				if(a.charAt(i) != b.charAt(n-i)){
					flags[1] = false;
				}
			}
			if (flags[2]){
				if (b.charAt(i) != b.charAt(n-i)){
					flags[2] = false;
				}
			}

			if (flags[3]){
				if (b.charAt(i) != a.charAt(n-i)){
					flags[3] = false;
				}
			}
			if (flags[0]||flags[1]||flags[2]||flags[3]){
				continue;
			} else {
				//这里需要判断原来两个字符串，从当前位置到n-i是否回文。懒得改算法了，直接遍历吧
				flags[0] = true;
				flags[2] = true;
				break;
			}
		}
		for(;i<(n+1)/2;i++) {
			if (flags[0]) {
				if (a.charAt(i) != a.charAt(n - i)) {
					flags[0] = false;
				}
			}

			if (flags[2]){
				if (b.charAt(i) != b.charAt(n-i)){
					flags[2] = false;
				}
			}
			if (flags[0] || flags[2]){
				continue;
			}else {
				return false;
			}
		}
		return flags[0]||flags[1]||flags[2]||flags[3];
	}

	public static int maximalNetworkRank(int n, int[][] roads) {
		//两次遍历。
		//每个城市自己联通的道路，用自己联通自己的路当做自己的所有联通道路数。
		int[][] rankArr = new int[n][n];

		for(int[] arr:roads) {
			rankArr[arr[0]][arr[0]] ++;
			rankArr[arr[1]][arr[1]] ++;

			rankArr[arr[0]][arr[1]] = 1;
			rankArr[arr[1]][arr[0]] = 1;
		}
		int max = 0;

		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				int curr = rankArr[i][i] +rankArr[j][j] - rankArr[i][j];
				if (curr> max){
					max = curr;
				}
			}
		}

		return  max;
	}


	public int maxDepth(String s) {
		//去除所有空括号，保证每个括号内有值
		s = s.replaceAll("()","");

		int currDepth = 0;
		int maxDepth = 0;
		for(char c:s.toCharArray()){
			if (c == '('){
				currDepth++;
			}else if(c == ')'){
				if (currDepth>maxDepth){
					maxDepth = currDepth;
				}
				currDepth --;
			}
		}

		return maxDepth;
	}


	static class Solution {
		public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
			TreeMap<Integer,Server> freeServer = new TreeMap<>();
			for(int i=0;i<k;i++){
				Server server = new Server(i);
				freeServer.put(i,server);
			}

			PriorityQueue<Server> serverTaskEndQueue = new PriorityQueue<>((s1,s2) ->(s1.taskEnd-s2.taskEnd));
			for (int i = 0; i < arrival.length; i++) {
				while(serverTaskEndQueue.size() != 0){
					if (serverTaskEndQueue.peek().taskEnd <= arrival[i]){
						Server server = serverTaskEndQueue.poll();
						freeServer.put(server.id,server);
					}else{
						break;
					}
				}
				if (freeServer.size() == 0){
					continue;
				}
				Integer index = freeServer.ceilingKey(i % k);
				if(index == null){
					//如果>=i%k找不到，那么从0开始找
					index = freeServer.ceilingKey(-1);
				}

				if(index != null){
					Server server = freeServer.get(index);
					server.addTask(arrival[i],load[i]);
					serverTaskEndQueue.add(server);
					freeServer.remove(index);
					break;
				}
			}

			List<Integer> resList = new ArrayList<>();
			int maxTaskNum = -1;
			while(serverTaskEndQueue.size()!=0){
				Server server = serverTaskEndQueue.poll();
				if (server.taskSum > maxTaskNum) {
					resList.clear();
					resList.add(server.id);
					maxTaskNum = server.taskSum;
				} else if (server.taskSum == maxTaskNum) {
					resList.add(server.id);
				}
			}
			for(Map.Entry<Integer,Server> entry:freeServer.entrySet()){
				Server server = entry.getValue();
				if (server.taskSum > maxTaskNum) {
					resList.clear();
					resList.add(server.id);
					maxTaskNum = server.taskSum;
				} else if (server.taskSum == maxTaskNum) {
					resList.add(server.id);
				}
			}
			return resList;
		}

		class Server{
			int id;
			int taskEnd;//上一次任务结束时间
			int taskSum;

			Server(int id) {
				this.id=id;
				this.taskEnd = -1;
				this.taskSum = 0;
			}

			boolean addTask(int currTime, int taskLoad) {
				this.taskSum++;
				this.taskEnd = currTime + taskLoad;
				return true;
			}
		}
	}

	public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
		int m = rowSum.length;
		int n = colSum.length;
		int[][] resArr = new int[m][n];
		//先按列扫描，再按行倒序借位。
		for (int i = 0; i < n; i++) {
			resArr[0][i] = colSum[i];
		}

		int currColumn = 0;
		for (int i = m - 1; i > 0; i--) {
			int currRowSum = rowSum[i];
			for (int j = currColumn; j < n; j++) {
				if (resArr[0][j] < currRowSum) {
					resArr[i][j] = resArr[0][j];
					resArr[0][j] = 0;
					currRowSum -= resArr[i][j];
					currColumn++;
				} else {
					resArr[i][j] = currRowSum;
					resArr[0][j] -= currRowSum;
					currColumn++;
					break;
				}
			}
		}

		return resArr;
	}

	public List<String> alertNames(String[] keyName, String[] keyTime) {
		Map<String, PriorityQueue<Integer>> timeMap = new TreeMap<>();
		for (int i = 0; i < keyName.length; i++) {
			String[] timeArr = keyTime[i].split(":");
			int time = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);

			PriorityQueue<Integer> timeQueue = timeMap.getOrDefault(keyName[i], new PriorityQueue<>());
			timeQueue.add(time);
			timeMap.put(keyName[i], timeQueue);
		}

		List<String> resultList = new ArrayList<>();
		for (Map.Entry<String, PriorityQueue<Integer>> entry : timeMap.entrySet()) {
			PriorityQueue<Integer> timeQueue = entry.getValue();
			if (timeQueue.size() < 3) {
				continue;
			} else {
				int first = timeQueue.poll();
				int second = timeQueue.poll();
				while (timeQueue.size() > 0) {
					int third = timeQueue.poll();
					if (third - first <= 60) {
						resultList.add(entry.getKey());
						break;
					} else {
						first = second;
						second = third;
					}
				}
			}
		}

		return resultList;
	}

	class ParkingSystem {
		Map<Integer, Integer> carMap = new HashMap<>(4);

		public ParkingSystem(int big, int medium, int small) {
			carMap.put(1, big);
			carMap.put(2, medium);
			carMap.put(3, small);
		}

		public boolean addCar(int carType) {
			int curr = carMap.getOrDefault(carType, 0);
			if (curr == 0) {
				return false;
			} else {
				carMap.put(carType, curr - 1);
				return true;
			}
		}
	}
}
