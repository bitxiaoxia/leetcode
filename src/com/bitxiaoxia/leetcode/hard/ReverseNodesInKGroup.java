package com.bitxiaoxia.leetcode.hard;

import com.bitxiaoxia.leetcode.utils.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * Created by Bitxiaoxia on 2020/9/21.
 */
public class ReverseNodesInKGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode first = dummy;
		ListNode curr = dummy.next;
		int pos = 0;
		Deque<ListNode> nodeQueue = new ArrayDeque();
		nodeQueue.clear();
		while(curr != null){
			first.next = curr;
			while(pos < k && curr!=null){
				pos++;
				nodeQueue.push(curr);
				curr = curr.next;
			}
			//pos = k;可以开始反转了，起始节点为first
			if(pos == k){
				while(nodeQueue.size() > 0){
					ListNode temp = nodeQueue.pop();
					first.next = temp;
					first = temp;
				}
				pos = 0;
			}
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);

		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;

		ReverseNodesInKGroup s = new ReverseNodesInKGroup();
		ListNode n = s.reverseKGroup(l1,3);
		while(n!=null){
			System.out.println(n.val);
			n = n.next;
		}
	}

}
