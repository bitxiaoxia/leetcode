package com.bitxiaoxia.leetcode.contest;

import com.bitxiaoxia.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bitxiaoxia on 2020/10/12.
 */
public class Contest209 {
	public static void main(String[] args) {
		String[] arr = {"bella","label","roller"};
		commonChars(arr);
	}


	public static List<String> commonChars(String[] A) {
		List<String> resultList= new ArrayList();
		if(A.length == 0){
			return resultList;
		}
		//引用计数
		int[] charNum = new int[26];
		for(char c: A[0].toCharArray()){
			int pos = c-'a';
			charNum[pos] ++;
		}

		//当前队列
		for(int i=1;i<A.length;i++){
			int[] tempNum = new int[26];
			for(char c: A[i].toCharArray()){
				int pos = c- 'a';
				tempNum[pos] ++;
			}

			for(int j=0;j<26;j++){
				charNum[j] = Math.min(charNum[j],tempNum[j]);
			}
		}

		//输出结果
		for(int i=0;i<26;i++){
			char c = (char)('a'+i);
			System.out.println(c+" "+charNum[i]);
			for(int j=0;j<charNum[i];j++){
				resultList.add(String.valueOf(c));
			}
		}
		return resultList;
	}

	public static boolean isEvenOddTree(TreeNode root) {
		List<Integer> depthCurrentValue = new ArrayList<>();//记录下当前每层的值。
		return dfs(root, depthCurrentValue, 0);
	}

	public static boolean dfs(TreeNode node, List<Integer> depthValue, int depth) {
		if (node == null) {
			return true;
		}
		int val = node.val;
		if (depth % 2 == 0) {
			//偶数层，值为奇数且严格递增。
			if (val % 2 == 0) {
				return false;
			}
			if (depthValue.size() <= depth) {
				depthValue.add(val);
			} else {
				if (depthValue.get(depth) >= val) {
					return false;
				} else {
					depthValue.set(depth, val);
				}
			}
		} else {
			if (val % 2 != 0) {
				return false;
			}

			if (depthValue.size() <= depth) {
				depthValue.add(val);
			} else {
				if (depthValue.get(depth) <= val) {
					return false;
				} else {
					depthValue.set(depth, val);
				}
			}
		}

		return dfs(node.left, depthValue, depth + 1) && dfs(node.right, depthValue, depth + 1);
	}

	public int specialArray(int[] nums) {
		Arrays.sort(nums);

		int n = nums.length;
		for (int i = 0; i < n; i++) {
			//n-i表示还有多少个数，需要判断当前值大于等于a，且上一个值比a小
			if (nums[i] >= n - i) {
				//当前值比较大
				if (i == 0 || nums[i - 1] < n - i) {
					//前一个值比当前值
					return n - i;
				}
			}
		}
		return -1;
	}

	public static void changeToArr(String s) {
		s = s.replace("[", "{");
		s = s.replace("]", "}");
		System.out.println(s);
	}
}
