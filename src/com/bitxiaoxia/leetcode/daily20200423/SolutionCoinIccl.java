package com.bitxiaoxia.leetcode.daily20200423;

/**
 * @author JasonZhang
 * @date 2020/4/23 17:53
 */
public class SolutionCoinIccl {
	public static int waysToChange(int n) {
		n = n - n % 5;
		int[] coins = {25, 10, 5, 1};
		long result = 0;

		int num25 = n / 25;
		for (int j = num25; j >= 0; j--) {
			int remain25 = n - j * 25;
			int num10 = remain25 / 10;
			for (int k = num10; k >= 0; k--) {
				int remain10 = remain25 - k * 10;
				int num5 = remain10 / 5;
				result += (num5 + 1);
			}
		}
		result = result % 1000000007;
		return (int) result;
	}

	public static void main(String[] args) {
		int n = 0;
		for (int i = 1; i < 200; i += 5) {
			int m = waysToChange(i);
			System.out.println(i + " " + m + " " + (m - n));
			n = m;
			if (i == 1) {
				i--;
			}
		}

	}
}
