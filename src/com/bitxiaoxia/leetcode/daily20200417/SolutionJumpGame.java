package com.bitxiaoxia.leetcode.daily20200417;

/**
 * @author JasonZhang
 * @date 2020/4/17 17:13
 */
public class SolutionJumpGame {
	public static boolean canJump(int[] nums) {
		if (nums.length==1){
			return true;
		}
		//选1，是因为0在数组中，是个特殊的数字。
		int n = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] >= n) {
				n = 1;
			} else {
				n++;
			}
		}
		return n == 1;
	}

	public static void main(String[] args) {
		int[][] testArray = {{2,3,1,1,4},
				{3,2,1,0,4},
				{0,1},
				{0},
				{2,0,0}};

		for (int[] array:testArray){
			System.out.println(canJump(array));
		}
	}
}
