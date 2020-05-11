package com.bitxiaoxia.leetcode.daily20200511;

/**
 * @author JasonZhang
 * @date 2020/5/11 19:32
 */
public class SolutionPowN {
	public double myPow(double x, int n) {
		//一开始忘了考虑这里了，导致死循环。
		if (n == Integer.MIN_VALUE) {
			return myPow(1 / x, Integer.MAX_VALUE) * 1 / x;
		}
		if (n < 0) {
			return myPow(1 / x, -n);
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}

		double tmp = myPow(x, n / 2);
		if (n % 2 == 0) {
			return tmp * tmp;
		} else {
			return tmp * tmp * x;
		}

	}
}
