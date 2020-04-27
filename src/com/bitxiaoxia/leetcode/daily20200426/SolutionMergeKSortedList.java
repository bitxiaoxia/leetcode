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

        mergeTwoNode(nodes, left, mid + 1);
    }

    private void mergeTwoNode(ListNode[] nodes, int left, int right) {
        ListNode curLeft = nodes[left];
        ListNode curRight = nodes[right];
        if (curRight == null){
        	return;
		}
        if (curLeft == null){
			nodes[left] = curRight;
			return;
		}
        ListNode rootNode;
        if (curLeft.val <= curRight.val) {
            rootNode = curLeft;
            curLeft = curLeft.next;
        } else {
            rootNode = curRight;
            curRight = curRight.next;
        }
        ListNode currNode = rootNode;
        while (true) {
            if (curLeft == null) {
                currNode.next = curRight;
                break;
            }
            if (curRight == null) {
                currNode.next = curLeft;
                break;
            }
            if (curLeft.val <= curRight.val) {
                currNode.next = curLeft;
                curLeft = curLeft.next;
            } else {
                currNode.next = curRight;
                curRight = curRight.next;
            }
            currNode = currNode.next;
        }
        nodes[left] = rootNode;
    }

    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode addNext(int x) {
            this.next = new ListNode(x);
            return this.next;
        }
    }

    private static void printNode(ListNode node){
    	while(node!=null){
    		System.out.print(node.val+" ");
    		node = node.next;
		}
    	System.out.println();
	}

    public static void main(String[] args) {
		ListNode node1 = new ListNode(-1);
		node1.addNext(5);
		ListNode node2 = new ListNode(1);
		node2.addNext(4).addNext(6);
		ListNode node3 = new ListNode(4);
		node3.addNext(5).addNext(6);

		ListNode[] nodes = {null,node1,node2,node3};

		SolutionMergeKSortedList solution = new SolutionMergeKSortedList();
		printNode(solution.mergeKLists(nodes));
		for (int i =0;i<nodes.length;i++){
			printNode(nodes[i]);
		}
    }
}
