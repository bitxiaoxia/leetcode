package com.bitxiaoxia.leetcode.daily20200430;

/**
 * @author JasonZhang
 * @date 2020/4/30 18:33
 */
public class SolutionUglyNumber {
	public boolean isUgly(int num) {
		if (num == 0) {
			return false;
		}
		while (num % 2 == 0) {
			num = num / 2;
		}
		while (num % 3 == 0) {
			num = num / 3;
		}
		while (num % 5 == 0) {
			num = num / 5;
		}
		return num == 1;
	}
}
