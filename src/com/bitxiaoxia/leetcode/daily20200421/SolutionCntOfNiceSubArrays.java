package com.bitxiaoxia.leetcode.daily20200421;

/**
 * @author JasonZhang
 * @date 2020/4/21 11:35
 */
public class SolutionCntOfNiceSubArrays {
	public static int numberOfSubarrays(int[] nums, int k) {
		int[] oddArr = new int[nums.length + 2];
		oddArr[0] = 0;
		int pos = 1;
		for (int i = 0; i < nums.length; i++) {
			if ((nums[i] & 1) == 1) {
				oddArr[pos] = i + 1;
				pos++;
			}
		}
		oddArr[pos] = nums.length + 1;
		pos++;//pos之后的意义是oddArr的实际长度了。
		int result = 0;
		for (int i = 1; i < pos - k; i++) {
			result += (oddArr[i] - oddArr[i - 1]) * (oddArr[i + k] - oddArr[i + k - 1]);
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = {2044,96397,50143};
		int k = 1;
//		System.out.println(numberOfSubarrays(nums, k));
		System.out.println((96397 & 1));
	}
}
