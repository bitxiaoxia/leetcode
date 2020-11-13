package com.bitxiaoxia.leetcode.daily20200514;

/**
 * @author JasonZhang
 * @date 2020/5/14 17:25
 */
public class SolutionContinuousSubarraySum {
	public boolean checkSubarraySum(int[] nums, int k) {
		int length = nums.length;
		if (length < 2) {
			return false;
		}
		if (k == 0) {
			int n = 0;
			for (int i:nums){
				if (i==0){
					n++;
				}else {
					n=0;
				}
				if (n>=2){
					return true;
				}
			}
			return false;
		}

		int[] dp = new int[length];

//		int[] mod = new int[length];
		//初始化状态数组
		for (int i = 0; i < length; i++) {
			nums[i] = nums[i] % k;
			if (i == 0) {
				dp[0] = nums[0];
			} else {
				dp[i] = (dp[i - 1] + nums[i]) % k;
				if (dp[i] == 0) {
					return true;
				}
			}
		}
		//初始化完成后dp[n] 表示第0位到第n位的余数值。
		//之后每次减掉对应位置的值即可。
		for (int i = 0; i < length - 1; i++) {
			int m = nums[i];
			//第几次迭代
			for (int j = i + 1; j < length; j++) {
				dp[j] = (dp[j] - m) % k;
				if (j - i >= 2 && dp[j] == 0) {
					return true;
				}
			}
		}
		return false;
	}
}
