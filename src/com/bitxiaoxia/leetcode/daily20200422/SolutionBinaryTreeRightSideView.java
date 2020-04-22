package com.bitxiaoxia.leetcode.daily20200422;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonZhang
 * @date 2020/4/22 12:07
 */
public class SolutionBinaryTreeRightSideView {
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		dfs(root, result, 0);
		return result;
	}

	private void dfs(TreeNode node, List<Integer> list, int nodeDepth) {
		if (node == null){
			return;
		}
		if (nodeDepth >= list.size()) {
			list.add(node.val);
		}
		if (node.right != null) {
			dfs(node.right, list, nodeDepth + 1);
		}
		if (node.left != null) {
			dfs(node.left, list, nodeDepth + 1);
		}
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

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		node1.left=node2;
		node1.right=node3;
		node2.right=node5;
		node3.right=node4;

		SolutionBinaryTreeRightSideView solu=  new SolutionBinaryTreeRightSideView();
		List<Integer> list = solu.rightSideView(node1);

		System.out.println(list);

	}

}
