package com.bitxiaoxia.leetcode.daily20200421;

/**
 * @author JasonZhang
 * @date 2020/4/21 19:03
 */
public class SolutionClimbingStairs {
	public int climbStairs(int n) {
		//n>46时，会溢出
		int result = 1;
		for (int m = 1; m <= n - m; m++) {
			result += calC(n-m, m);
		}
		return result;
	}

	private long calC(int n, int m) {
		//这里用Long，因为int型会溢出。。
		long result = 1;
		for (int max = n, min = 1; max > n - m; max--,min++) {
			result *= max;
			result /= min;
		}
		return result;
	}

	public static void main(String[] args) {
		SolutionClimbingStairs stairs = new SolutionClimbingStairs();
		for (int i = 0; i < 100; i++) {
			System.out.println(i + " " + stairs.climbStairs(i));
		}
//		System.out.println(stairs.climbStairs(32));
	}
}
