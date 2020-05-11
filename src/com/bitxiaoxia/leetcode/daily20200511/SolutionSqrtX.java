package com.bitxiaoxia.leetcode.daily20200511;

/**
 * @author JasonZhang
 * @date 2020/5/11 19:35
 */
public class SolutionSqrtX {
	public int mySqrt(int x) {
		if (x <= 0) {
			return 0;
		}
		if (x <= 3) {
			return 1;
		}

		//第一种不使用二分查找，直接处理
		// for(long i=2;;i++){
		//     if(i*i>x){
		//         return (int)(i-1);
		//     }
		// }

		//二分查找的方式
		int left = 0;
		int right = x / 2;
		int ans = 0;//标记这里
		while (left <= right) {
			int mid = left + (right - left) / 2;
			long res = ((long) mid) * mid;
			if (res <= x) {
				//可以保证ans*ans 一定小于等于x
				ans = mid;
				//避免死循环
				left = mid + 1;
			} else {
				//ans一定不在这个区间
				right = mid - 1;
			}
		}
		return ans;
	}

}
