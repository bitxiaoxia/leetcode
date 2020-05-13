package com.bitxiaoxia.leetcode.daily20200513;

/**
 * @author JasonZhang
 * @date 2020/5/13 20:03
 */
public class SolutionInterview21 {
	public int[] exchange(int[] nums) {
		return method2(nums);
	}

	/**
	 * 最朴素的思想，遍历两次
	 *
	 * @param nums
	 * @return
	 */
	private int[] method1(int[] nums) {
		int[] result = new int[nums.length];
		int pos = 0;
		for (int i : nums) {
			if (i % 2 == 1) {
				result[pos] = i;
				pos++;
			}
		}

		for (int i : nums) {
			if (i % 2 == 0) {
				result[pos] = i;
				pos++;
			}
		}
		return result;
	}

	/**
	 * 首尾指针，遍历一次
	 *
	 * @param nums
	 * @return
	 */
	private int[] method2(int[] nums) {
		int[] result = new int[nums.length];
		int left = 0;
		int right = nums.length - 1;
		for (int i : nums) {
			if (i % 2 == 1) {
				result[left] = i;
				left++;
			} else {
				result[right] = i;
				right--;
			}
		}
		return result;
	}

	/**
	 * 首尾指针+避免借助辅助数组
	 *
	 * @param nums
	 * @return
	 */
	private int[] method3(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int i = nums[left];
			if (i % 2 == 0) {
				//偶数，需要往后放。
				while (right > left) {
					if (nums[right] % 2 == 1) {
						//奇数，调换
						nums[left] = nums[right];
						nums[right] = i;
						right --;
						break;
					} else {
						right--;
					}
				}
			}
			left++;
		}
		return nums;
	}
}
