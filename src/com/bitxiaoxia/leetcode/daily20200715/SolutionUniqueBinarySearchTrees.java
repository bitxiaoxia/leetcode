package com.bitxiaoxia.leetcode.daily20200715;

import java.util.Arrays;

/**
 * Created by Bitxiaoxia on 2020/7/15.
 */
public class SolutionUniqueBinarySearchTrees {
	public int numTrees(int n) {
		if (n<=0){
			return 0;
		}
		int[] dp = new int[n+1];
		Arrays.fill(dp,0);
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 0;j<i;j++){
				dp[i] += dp[j]*dp[i-j-1];
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		SolutionUniqueBinarySearchTrees solu = new SolutionUniqueBinarySearchTrees();
		System.out.println(solu.numTrees(1));
		System.out.println(solu.numTrees(2));
		System.out.println(solu.numTrees(3));
		System.out.println(solu.numTrees(4));
		System.out.println(solu.numTrees(5));
		System.out.println(solu.numTrees(16));
	}
}
