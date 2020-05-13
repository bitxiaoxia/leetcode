package com.bitxiaoxia.leetcode.daily20200513;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author JasonZhang
 * @date 2020/5/13 16:17
 */
public class SolutionBinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
//		List<List<Integer>> resList = new ArrayList<>();
//		dfs(root, 0, resList);
//		return resList;
		return bfs(root);
	}

	public List<List<Integer>> bfs(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null){
			return result;
		}
		Deque<TreeNode> nodeQueue = new ArrayDeque<>();
		nodeQueue.addLast(root);
		int currentLevel = 0;
		int currentLevelNodeNum = 1;
		int nextLevelNodeNum = 0;
		while (true) {
			TreeNode node = nodeQueue.pollFirst();
			if (node == null) {
				break;
			}

			if (node.left != null) {
				nodeQueue.addLast(node.left);
				nextLevelNodeNum++;
			}
			if (node.right != null) {
				nodeQueue.addLast(node.right);
				nextLevelNodeNum++;
			}

			List<Integer> currentList;
			if (result.size() <= currentLevel) {
				currentList = new ArrayList<>();
				result.add(currentList);
			} else {
				currentList = result.get(currentLevel);
			}
			currentList.add(node.val);

			currentLevelNodeNum -- ;
			if(currentLevelNodeNum == 0){
				currentLevel ++;
				currentLevelNodeNum = nextLevelNodeNum;
				nextLevelNodeNum = 0;
			}
		}
		return result;
	}

	public void dfs(TreeNode node, int currentLevel, List<List<Integer>> result) {
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
