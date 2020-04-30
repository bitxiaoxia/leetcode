package com.bitxiaoxia.leetcode.daily20200430;

import java.util.Arrays;

/**
 * @author JasonZhang
 * @date 2020/4/30 14:46
 */
public class SolutionHappyNumber {
	private int[] happyArr={1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193};
	public boolean isHappy(int n) {
		while (n>200){
			n = calSquareSum(n);
		}
		for (int i:happyArr){
			if (i==n){
				return true;
			}
		}
		return false;
	}

	private static boolean calOnce(boolean[] originArray) {
		boolean[] temp = Arrays.copyOf(originArray,originArray.length);
		boolean flag = true;
		for (int i = 0; i < originArray.length; i++) {
			if (originArray[i] != temp[calSquareSum(i)]){
				flag = false;
				originArray[i] = temp[calSquareSum(i)];
			}
		}
		return flag;
	}

	public static int calSquareSum(int n) {
		int sum = 0;
		while (n != 0) {
			int x = n % 10;
			n = n / 10;
			sum += x * x;
		}
		return sum;
	}

	public static void main(String[] args) {
		boolean[] arr = new boolean[200];
		arr[1] = true;
		while (true) {
			boolean result = calOnce(arr);
			if (result) {
				break;
			}
		}
		for (int i = 0; i < 200; i++) {
			if (arr[i]){
				System.out.print(i+", ");
			}
		}
	}
}
