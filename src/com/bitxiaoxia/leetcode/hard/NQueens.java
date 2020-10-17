package com.bitxiaoxia.leetcode.hard;

import com.bitxiaoxia.leetcode.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens/
 * Created by Bitxiaoxia on 2020/10/17.
 */
public class NQueens {
	public static void main(String[] args) {
		NQueens solution = new NQueens();
		solution.solveNQueens(4);
	}
	//N皇后2.https://leetcode-cn.com/problems/n-queens-ii/
	public int totalNQueens(int n) {
		//可以简单实用之前的代码，返回数组长度。
		return solveNQueens(n).size();

		//原来的解法由于需要记录返回结果，所以耗内存较大，这个只用返回符合条件的个数，所以重新写一个。

	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> resultList = new ArrayList<>();

		List<String> currentResult = new ArrayList<String>();

		backTrace(resultList,currentResult,n);

		return resultList;
	}

	private void backTrace(List<List<String>> resultList, List<String> currentList,int n){
		if(currentList.size() == n){
//			PrintUtils.print(currentList);
			//加入结果
			resultList.add(new ArrayList<>(currentList));
		}

		//标记本行哪一位可用。
		boolean[] usedArr = new boolean[n];
		//当前行
		int currentLine = currentList.size();
		for(int i=0;i<currentList.size();i++){
			String lineValue = currentList.get(i);
			int lineDiff = currentLine - i;//行差
			for(int j=0;j<lineValue.length();j++){
				if(lineValue.charAt(j) == 'Q'){
					//当前列设置为空
					usedArr[j] = true;
					if(j+lineDiff < n){
						usedArr[j+lineDiff] = true;
					}
					if(j-lineDiff >=0){
						usedArr[j-lineDiff] = true;
					}
					break;
				}
			}
		}
		char[] chars = new char[n];
		Arrays.fill(chars,'.');
		//根据标记进行回溯
		for(int i=0;i<n;i++){
			if(!usedArr[i]){
				//可用
				chars[i] = 'Q';
				String line = new String(chars);
				currentList.add(line);

				backTrace(resultList,currentList,n);

				currentList.remove(currentList.size() -1);
				chars[i] = '.';
			}
		}
	}
}
