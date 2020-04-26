package com.bitxiaoxia.leetcode.daily20200426;


/**
 * @author JasonZhang
 * @date 2020/4/26 12:10
 */
public class SolutionMergeKSortedList {
	public ListNode mergeKLists(ListNode[] lists) {
		int length = lists.length;
		if (length == 0) {
			return null;
		}

		mergeAndSort(lists, 0, lists.length - 1);
		return lists[0];
	}

	/**
	 * 合并排序，把右侧所有的node合并到left节点。
	 *
	 * @param nodes 原来的节点
	 * @param left
	 * @param right
	 * @return
	 */
	private void mergeAndSort(ListNode[] nodes, int left, int right) {
		if (left == right) {
			return;
		}
		int mid = left + (right - left) / 2;
		mergeAndSort(nodes, left, mid);
		mergeAndSort(nodes, mid + 1, right);

		mergeTwoNode(nodes,left, right);
	}

	private void mergeTwoNode(ListNode[] nodes,int left, int right) {
		ListNode leftNode = nodes[left];
		ListNode rightNode = nodes[right];

		ListNode temp;

	}

	//Definition for singly-linked list.
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
