package com.bitxiaoxia.leetcode.daily20200424;

import java.util.Arrays;

/**
 * @author JasonZhang
 * @date 2020/4/24 12:28
 */
public class SolutionReverseOrderInArray {
    public int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int[] copy = Arrays.copyOf(nums, nums.length);
        int[] temp = new int[nums.length];

        return cnt(copy, 0, nums.length - 1, temp);
    }

    private int cnt(int[] originArray, int left, int right, int[] sortedTemp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftPairs = cnt(originArray, left, mid, sortedTemp);
        int rightPairs = cnt(originArray, mid + 1, right, sortedTemp);

        int mergeCnt = merge(originArray, left, mid, right, sortedTemp);
        return leftPairs + rightPairs + mergeCnt;
    }

    private int merge(int[] originArray, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = originArray[i];
        }

        int result = 0;
        for (int pos = left, i = left, j = mid + 1; pos <= right; pos++) {
            if (i == mid + 1) {
                originArray[pos] = temp[j];
                j++;
            } else if (j == right + 1) {
                originArray[pos] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                originArray[pos] = temp[i];
                i++;
            } else {
                originArray[pos] = temp[j];
                j++;
                result += (mid - i + 1);
            }
        }
        return result;
    }

    private static int force(int[] nums) {
        if (nums.length == 0) {
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
        SolutionReverseOrderInArray solution = new SolutionReverseOrderInArray();
        System.out.println(solution.reversePairs(array));
    }
}
