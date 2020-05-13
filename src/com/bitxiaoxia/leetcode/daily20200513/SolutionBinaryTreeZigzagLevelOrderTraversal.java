package com.bitxiaoxia.leetcode.daily20200513;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author JasonZhang
 * @date 2020/5/13 19:47
 */
public class SolutionBinaryTreeZigzagLevelOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> resList = new ArrayList<>();
		dfs(root, 0, resList);
		return resList;
	}

	public void dfs(TreeNode node, int currentLevel, List<List<Integer>> result) {
		if (node == null) {
			return;
		}
		List<Integer> currentList;
		if (result.size() <= currentLevel) {
			currentList = new LinkedList<>();
			result.add(currentList);
		} else {
			currentList = result.get(currentLevel);
		}
		if (currentLevel % 2 == 1) {
			currentList.add(0, node.val);
		} else {
			currentList.add(node.val);
		}

		dfs(node.left, currentLevel + 1, result);
		dfs(node.right, currentLevel + 1, result);
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
