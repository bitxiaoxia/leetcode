package com.bitxiaoxia.leetcode.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Bitxiaoxia on 2020/10/17.
 */
public class BlContest37 {
	public static void main(String[] args) {
		System.out.println(numberOfSets(42,25));//630775896
		System.out.println(numberOfSets(3,1));
		System.out.println(numberOfSets(3,2));
		System.out.println(numberOfSets(5,3));
		System.out.println(numberOfSets(30,7));
//		long a = 3796297200L;
//		long b = 1585245586044L;
//		System.out.println(a %MOD_VALUE);
	}

	class Fancy {
		List<Integer> list;
		long addValue;
		long multValue;
		int MOD_VALUE = 1000000007;
		public Fancy() {
			list = new ArrayList<>();
			addValue = 0;
			multValue = 1;
		}

		public void append(int val) {
			list.add(val);
		}

		public void addAll(int inc) {
			addValue+=inc;
		}

		public void multAll(int m) {
			addValue *= m;
			multValue *= m;
		}

		public int getIndex(int idx) {
			if (idx>list.size()||idx<0){
				return -1;
			} else {
				return (int)(((multValue%MOD_VALUE	* list.get(idx))%MOD_VALUE + addValue%MOD_VALUE)%MOD_VALUE);
			}
		}
	}

	public static void changeToArr(String s) {
		s = s.replace("[", "{");
		s = s.replace("]", "}");
		System.out.println(s);
	}

	static int MOD_VALUE = 1000000007;


	public static int numberOfSets(int n, int k) {
		//递归写法超时了。填表
		//dp
		//方案数 dp[n][k] = dp[n-1][k-1] + dp[n-2][k-1] + ...+ dp[2][k-1]
		//			        +dp[n-1][k]

		//dp[n-1][k]= dp[n-2][k-1] + ....+dp[2][k-1] + dp[n-2][k]

		//dp[n][k] - dp[n-1][k] = dp[n-1][k-1] + dp[n-1][k] -dp[n-2][k]
		//dp[n][k] = 2dp[n-1][k] + dp[n-1][k-1] -dp[n-2][k]
		//if(n <= k) dp[n][k] = 0
		//if(n==k+1) dp[n][k] = 1
//		if (n<=k){
//			return 0;
//		}
//		if (k==1){
//			return n*(n-1)/2;
//		}
//		if(n==k+1){
//			return 1;
//		}
//
//		int temp = (numberOfSets(n-1,k)*2) % MOD_VALUE + numberOfSets(n-1,k-1) - numberOfSets(n-2,k);
//		return temp %MOD_VALUE;

		int[][] dp = new int[n+1][k+1];
		//逐行扫描
		for(int i=2;i<n+1;i++){
			for(int j=1;j<k+1;j++){
				if (i<=j){
					dp[i][j] = 0;
				}else if(i==j+1){
					dp[i][j] = 1;
				}else if(j==1){
					dp[i][j] = i*(i-1)/2;
				}else{
					dp[i][j] = ((dp[i-1][j] * 2)% MOD_VALUE + dp[i-1][j-1] - dp[i-2][j]) % MOD_VALUE;
					if (dp[i][j] < 0){
						dp[i][j] += MOD_VALUE;
					}
				}

//				System.out.print(dp[i][j] + " ");
			}
//			System.out.println();
		}

		return dp[n][k];
	}



	public double trimMean(int[] arr) {
		int length = arr.length;
		int heapSize = length / 20;

		Arrays.sort(arr);
		double total = 0;
		for(int i=heapSize;i<length-heapSize;i++){
			total += arr[i];
		}

		return total/(length - 2*heapSize);
	}
}
