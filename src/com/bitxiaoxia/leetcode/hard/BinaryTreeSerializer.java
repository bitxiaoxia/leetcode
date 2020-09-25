package com.bitxiaoxia.leetcode.hard;

import com.bitxiaoxia.leetcode.utils.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Bitxiaoxia on 2020/9/25.
 */
public class BinaryTreeSerializer {
	static final TreeNode NULL_NODE = new TreeNode(-2);//由于空节点不允许为空，所以用这个来代替。
	static final char GAP_CHAR = ',';

	public static void main(String[] args) {
		BinaryTreeSerializer s = new BinaryTreeSerializer();
		TreeNode root = s.deserialize("[1]");
		System.out.println(s.serialize(root));
	}

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "[null]";
		}

		Deque<TreeNode> nodeQueue = new ArrayDeque<>();
		StringBuilder builder = new StringBuilder("[");
		nodeQueue.add(root);
		while(nodeQueue.size()!=0){
			TreeNode node= nodeQueue.poll();
			if (node == NULL_NODE){
				builder.append("null").append(GAP_CHAR);
			}else{
				builder.append(node.val).append(GAP_CHAR);
				nodeQueue.add(node.left == null?NULL_NODE:node.left);
				nodeQueue.add(node.right == null?NULL_NODE:node.right);
			}
		}

		while(builder.length()>=5){
			//去除最后的null节点
			String s = builder.substring(builder.length()-5,builder.length());
			if (s.equals("null,")){
				builder.delete(builder.length()-5,builder.length());
			}else{
				break;
			}
		}
		builder.deleteCharAt(builder.length()-1);
		builder.append("]");
		return builder.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.equals("[null]")) {
			return null;
		}
		data = data.substring(1, data.length() - 1);
		String[] arr = data.split(",");
		Deque<TreeNode> queue = new ArrayDeque<>();
		TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

		queue.add(root);
		boolean isLeftNode = true;
		for(int i=1;i<arr.length;i++){
			String s = arr[i];
			TreeNode fatherNode = queue.peek();
			if (!isLeftNode){
				queue.poll();
			}
			if (s.equals("null")) {

			}else{
				TreeNode node = new TreeNode(Integer.parseInt(s));
				if (isLeftNode){
					fatherNode.left = node;
				}else {
					fatherNode.right = node;
				}
				queue.add(node);
			}
			isLeftNode = !isLeftNode;
		}
		return root;
	}
}
