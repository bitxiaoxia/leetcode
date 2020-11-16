package com.bitxiaoxia.interview.tantan;

import com.bitxiaoxia.leetcode.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonZhang
 * @date 2020/11/13 9:47
 */
public class TestTantan {
	public static void main(String[] args) {
		int[][] arr = {{1,2,3},{4,5},{7,8,9,10}};
		PrintUtils.print(combineArrays(arr));

		//1 000 000
		//key score : ykk:[userId]
	}

	private static List<Integer> combineArrays(int[][] sourceArr){
		int n= sourceArr.length;
		List<Integer> midList = new ArrayList<>();
		for(int i=0;;i++){
			boolean flag = false;
			for(int j=0;j<n;j++){
				if(sourceArr[j] .length>i){
					midList.add(sourceArr[j][i]);
					flag = true;
				}
			}
			if(!flag){
				break;
			}
		}

		return midList;
	}
}
