package com.bitxiaoxia.leetcode.daily20200503;

/**
 * @author JasonZhang
 * @date 2020/5/3 19:27
 */
public class SolutionMaximumSubarray {
	public int maxSubArray(int[] nums) {
		int max = nums[0];
		int before = nums[0];
		for (int i = 1; i < nums.length; i++) {
			before = Math.max(before + nums[i], nums[i]);
			max = Math.max(before, max);
		}
		return max;
	}

	public static void main(String[] args) {
		SolutionMaximumSubarray solution = new SolutionMaximumSubarray();
		int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		System.out.println(solution.maxSubArray(arr));
	}
}
