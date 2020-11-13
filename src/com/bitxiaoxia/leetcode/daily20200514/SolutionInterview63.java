package com.bitxiaoxia.leetcode.daily20200514;

/**
 * @author JasonZhang
 * @date 2020/5/14 17:03
 */
public class SolutionInterview63 {
	public int maxProfit(int[] prices) {
		int min=Integer.MAX_VALUE;
		int maxValue=0;
		for(int i:prices){
			int tempValue = i-min;
			min = Math.min(i,min);
			maxValue = Math.max(tempValue,maxValue);
		}
		return maxValue;
	}
}
