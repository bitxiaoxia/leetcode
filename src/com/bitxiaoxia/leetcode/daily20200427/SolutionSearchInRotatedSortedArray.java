package com.bitxiaoxia.leetcode.daily20200427;

/**
 * @author JasonZhang
 * @date 2020/4/27 17:27
 */
public class SolutionSearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		return searchBetweenBound(nums, 0, nums.length - 1, target);
	}

	private int searchBetweenBound(int[] nums, int left, int right, int target) {
		if (left == right) {
			if (nums[left] == target) {
				return left;
			} else {
				return -1;
			}
		}
		int mid = left + (right - left) / 2;
		if (nums[left] <= nums[right]) {
			//不存在拐点
			if (target < nums[left]) {
				return -1;
			} else if (target > nums[right]) {
				return -1;
			} else {
				int lIndex = searchBetweenBound(nums,left,mid,target);
				int rIndex = searchBetweenBound(nums,left,mid+1,target);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SolutionSearchInRotatedSortedArray solution = new SolutionSearchInRotatedSortedArray();
		solution.search(null, 1);
	}
}
