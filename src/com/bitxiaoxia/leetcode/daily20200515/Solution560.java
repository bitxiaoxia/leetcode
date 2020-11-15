package com.bitxiaoxia.leetcode.daily20200515;

/**
 * @author JasonZhang
 * @date 2020/5/15 10:57
 */
public class Solution560 {
	public int subarraySum(int[] nums, int k) {
		return dp(nums,k);
	}

	private int dp(int[] nums, int k) {
		int length = nums.length;
		int cnt = 0;
		int[] sums = new int[length];
		sums[0] = nums[0];
		if (sums[0] == k) {
			cnt++;
		}
		for (int i = 1; i < length; i++) {
			sums[i] = sums[i - 1] + nums[i];
			if (sums[i] == k) {
				cnt++;
			}
		}

		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				sums[j] = sums[j] - nums[i];
				if (sums[j] == k){
					cnt++;
				}
			}
		}

		return cnt;
	}


	private int force(int[] nums, int k) {
		int length = nums.length;
		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum = nums[i];
			if (sum == k) {
				cnt++;
			}
			for (int j = i + 1; j < length; j++) {
				sum += nums[j];
				if (sum == k) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		Solution560 solution560 = new Solution560();
		int[] arr = {1, 1, 1};
		System.out.println(solution560.subarraySum(arr, 2));
	}
}
