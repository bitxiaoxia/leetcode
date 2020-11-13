package com.bitxiaoxia.interview.didi;

import java.util.*;

/**
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * @author JasonZhang
 * @date 2020/8/22 9:52
 */
public class TestDidi {
	public String reverseWords(String s) {
		List<String> wordQueue = new ArrayList<>();
		s = s.trim();
		StringBuilder builder = new StringBuilder();
		for(char c:s.toCharArray()){
			if(c!=' '){
				builder.append(c);
			}else{
				if(builder.length() != 0){
					wordQueue.add(builder.toString());
					builder.delete(0,builder.length());
				}
			}
		}
		wordQueue.add(builder.toString());
		builder.delete(0,builder.length());

		for(int i = wordQueue.size()-1;i>=0;i--){
			builder.append(wordQueue.get(i)).append(" ");
		}
		return builder.substring(0,builder.length()-1);
	}
	private TreeNode reverseNode(TreeNode root){
		Deque<String> wordQueue = new ArrayDeque<String>;
		wordQueue.remove();
		if (root == null){
			return null;
		}else{
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;

			root.left = reverseNode(root.left);
			root.right = reverseNode(root.right);

			return root;
		}
	}

	class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public TreeNode getLeft() {
			return left;
		}
	}
}
