package com.bitxiaoxia.leetcode.daily20200424;

import java.util.Arrays;

/**
 * @author JasonZhang
 * @date 2020/4/24 12:28
 */
public class SolutionReverseOrderInArray {
	public static int reversePairs(int[] nums) {
		if (nums.length==0){
			return 0;
		}
		int[] sortedArray = new int[nums.length];
		Arrays.fill(sortedArray, 0);
		int pos = 0;
		sortedArray[0] = nums[0];
		int result = 0;
		for (int i = 1; i < nums.length; i++) {
			for (int j = pos; j >= -1; j--) {
				if (j == -1 || nums[i] >= sortedArray[j]) {
					sortedArray[j + 1] = nums[i];
					break;
				} else {
					//后移
					sortedArray[j + 1] = sortedArray[j];
					result++;
				}
			}
			pos++;
		}
		return result;
	}

	private static void printArray(int[] arr) {
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = {7, 5, 6, 4};
		System.out.println(reversePairs(array));
	}
}
