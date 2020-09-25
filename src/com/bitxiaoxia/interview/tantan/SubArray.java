package com.bitxiaoxia.interview.tantan;

import com.bitxiaoxia.leetcode.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个含不同整数的集合，返回其所有的子集。
 * 输入：[1,2,3]
 * 输出：
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class SubArray {
	public int[][] getSubArrays(int[] input) {
		int length = input.length;
		int resLength = (int) Math.pow(2, length);
		int[][] result = new int[resLength][length];

		for (int i = 0; i < resLength; i++) {
			//将i转成二进制，新数组中，1表示新数组含有该位，0表示不含有。
			//0 对应空数组。
			//resLength-1对应原数组。
			int temp = i;
			int j = 0;
			int pos = 0;
			while ((temp & Integer.MAX_VALUE) != 0) {
				if ((temp & 1) == 1) {
					result[i][j] = input[pos];
					j++;
				}
				temp = temp >> 1;
				pos++;
			}
		}
		return result;
	}

//	public List<List<Integer>> subsets(int[] nums) {
//		List<List<Integer>> resultList = new ArrayList<>();
//		List<Integer> path = new ArrayList<>();
//		backTrace(resultList,path,nums,0);
//
//		return resultList;
//	}
//
//	public void backTrace(List<List<Integer>> resultList,List<Integer> path,int[] nums,int pos){
//		if(pos == nums.length){
//			resultList.add(new ArrayList<>(path));
//			return;
//		}
//		//直接跳过这一位
//		backTrace(resultList,path,nums,pos+1);
//
//		path.add(nums[pos]);
//		backTrace(resultList,path,nums,pos+1);
//		path.remove(path.size() -1);
//	}


	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> path = new ArrayList<>();

		backTrace2(resultList,path,nums,0);
		return resultList;
	}

	public void backTrace2(List<List<Integer>> resultList,List<Integer> path,int[] nums,int pos){
		if(pos >= nums.length){
			PrintUtils.print(path);
			resultList.add(new ArrayList<>(path));
		}

		int same = 1;
		while(pos+same < nums.length && nums[pos] == nums[pos+same]){
			same++;
		}
		System.out.println("same"+same);
		//跳过当前位
		backTrace2(resultList,path,nums,pos+same);

		for(int i=0;i<same;i++){
			path.add(nums[pos]);
			backTrace2(resultList,path,nums,pos+same);
		}
		for(int i=0;i<same;i++){
			path.remove(path.size()-1);
		}
	}
	public static void main(String[] args) {
		SubArray subArray = new SubArray();
		int[] test = {1,2,2};
		List<List<Integer>> result = subArray.subsetsWithDup(test);
		for (int i = 0; i < result.size(); i++) {
			PrintUtils.print(result.get(i));
		}
	}
}
