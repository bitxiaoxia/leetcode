package com.bitxiaoxia.leetcode.contest.contest203;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bitxiaoxia on 2020/9/30.
 */
public class Pro {
	public static void main(String[] args) {
		int[] arr = {3,1,5,4,2};
		System.out.println(findLatestStep(arr,2));
	}

	public static int findLatestStep(int[] arr, int m) {
		int maxStep = -1;
		int n = arr.length;
		int[] res = new int[n];
		Arrays.fill(res,-1);
		//res数组每一位记录下1的起始位置，起始位置记录下当前队列的长度。
		//设置为1，有哪些改变？
		// 1 其中一边为1
		// 2 两边均为1
		// 3 两边均为0
		for(int i=0; i<n; i++){
			int pos = arr[i] -1;
			boolean left = false;
			boolean right = false;
			if(pos>0 && res[pos-1] != -1){
				left = true;
			}
			if(pos< n-1 && res[pos+1] != -1){
				right = true;
			}
			boolean checkFlag = false;
			if(!left && !right){
				res[pos] = 1;
				if(m == 1){
					maxStep = i+1;
				}
			}else if(!left && right){
				//右侧有值
				if(res[pos+1] ==m){
					checkFlag = true;
				}
				res[pos] = res[pos+1] +1;
				res[pos+res[pos+1]] = pos;
				if (res[pos] == m){
					maxStep = i+1;
					break;
				}
			}else if(left && !right){
				res[pos] = res[pos-1];
				res[res[pos-1]] ++;
				if(res[res[pos-1]] == m+1){
					checkFlag = true;
				}else if(res[res[pos-1]] == m){
					maxStep = i+1;
					break;
				}
			}else{
				//两侧均有值
				res[pos] = res[pos-1];
				if(res[res[pos-1]] == m || res[pos+1] == m){
					checkFlag = true;
				}
				res[res[pos-1]] += (res[pos+1] + 1);
				res[pos+res[pos+1]] =  res[pos-1];

				if(res[res[pos-1]] == m){
					maxStep = i+1;
					break;
				}
			}
			//到这里说明本次操作的1不足以组成m长度的字符串。或者有可能毁坏上次的字符串。

			//毁坏了之前的字符串
			for (int j = 0; j < n;) {
				if(res[j] <= 0){
					j++;
				}else{
					if(res[j] == m){
						maxStep = i+1;
						break;
					}
					j += res[j];
				}
			}

		}

		return maxStep;
	}

	public int maxCoins(int[] piles) {
		Arrays.sort(piles);
		int n = piles.length / 3;
		//不用担心溢出，最大结果为10亿
		int sum = 0;
		int l = piles.length-1;
		for (int i = 0; i < n; i++) {
			sum += piles[l-(2*i+1)];
		}
		return sum;
	}

	public static List<Integer> mostVisited(int n, int[] rounds) {
		boolean flag = false;//是否完成了一圈。
		int length = rounds.length;
		List<Integer> result = new ArrayList<>();

		if (n == 1) {
			result.add(1);
			return result;
		}

		for (int i = 1; i < length; i++) {
			if (rounds[i] < rounds[i - 1]) {
				//表示重新开始跑了一圈
				flag = true;
				break;
			}
		}

		if (flag) {
			if (rounds[length - 1] < rounds[0]) {
				//从1到rounds[length-1] 加上从rounds[0]-n
				for (int i = 1; i <= rounds[length - 1]; i++) {
					result.add(i);
				}
				for (int i = rounds[0]; i <= n; i++) {
					result.add(i);
				}
			} else if (rounds[length - 1] >= rounds[0]) {
				//从rounds[0]-rounds[length-1]
				for (int i = rounds[0]; i <= rounds[length - 1]; i++) {
					result.add(i);
				}
			}
		} else {
			//从rounds[0]-rounds[length-1]
			for (int i = rounds[0]; i <= rounds[length - 1]; i++) {
				result.add(i);
			}
		}
		return result;
	}
}
