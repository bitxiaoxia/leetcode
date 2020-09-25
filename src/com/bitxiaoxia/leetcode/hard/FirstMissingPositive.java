package com.bitxiaoxia.leetcode.hard;

/**
 * https://leetcode-cn.com/problems/first-missing-positive/
 * Created by Bitxiaoxia on 2020/9/21.
 */
public class FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for(int i=0;i<n;i++){
			if(nums[i] > 0 && nums[i] < i+1){
				//交换位置。
				int temp = nums[i];
				nums[i] = nums[temp-1];
				nums[temp-1] = temp;
				System.out.println(i+": ");
				for(int k:nums){
					System.out.print(k+" ");
				}
				System.out.println();
			}
		}

		for(int i=0;i<n;i++){
			if(nums[i] !=i+1){
				return i+1;
			}
		}
		return n+1;
	}

	public static void main(String[] args) {
		int[] arr = {11,1,6,11,5,5,-6,9,-3,9,5,4,2,-8,16,-6,-4,2,3};
		FirstMissingPositive s= new FirstMissingPositive();
		s.firstMissingPositive(arr);
		for(int i:arr){
			System.out.print(i+" ");
		}
	}
}
