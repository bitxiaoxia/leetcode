package com.bitxiaoxia.leetcode.daily20200501;

public class SolutionMergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode rootNode;
        if (l1.val <= l2.val) {
            rootNode = l1;
            l1 = l1.next;
        } else {
            rootNode = l2;
            l2 = l2.next;
        }
        ListNode currentNode = rootNode;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                currentNode.next = l2;
                break;
            }
            if (l2 == null) {
                currentNode.next = l1;
                break;
            }

            if (l1.val <= l2.val) {
                currentNode.next = l1;
                currentNode = currentNode.next;
                l1 = l1.next;
            } else {
                currentNode.next = l2;
                currentNode = currentNode.next;
                l2 = l2.next;
            }
        }

        return rootNode;
    }


    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
