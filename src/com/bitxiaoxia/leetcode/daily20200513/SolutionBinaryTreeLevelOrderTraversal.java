package com.bitxiaoxia.leetcode.daily20200513;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonZhang
 * @date 2020/5/13 16:17
 */
public class SolutionBinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> resList = new ArrayList<>();
		leverSearch(root, 0, resList);
		return resList;
	}

	public void leverSearch(TreeNode node, int currentLevel, List<List<Integer>> result) {
		if (node == null) {
			return;
		}
		List<Integer> currentList;
		if (result.size() <= currentLevel) {
			currentList = new ArrayList<>();
			result.add(currentList);
		} else {
			currentList = result.get(currentLevel);
		}
		currentList.add(node.val);
		leverSearch(node.left, currentLevel + 1, result);
		leverSearch(node.right, currentLevel + 1, result);
	}

	// Definition for a binary tree node.
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
