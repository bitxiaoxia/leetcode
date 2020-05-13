package com.bitxiaoxia.leetcode.daily20200512;

/**
 * @author JasonZhang
 * @date 2020/5/12 12:14
 */
class MinStack {

	/**
	 * initialize your data structure here.
	 */
	public MinStack() {
		firstNode = null;
	}

	Node firstNode;

	class Node {
		int value;
		Node next;
		int minValue;

		Node(int value, Node next) {
			this.value = value;
			this.next = next;
			if (next != null) {
				if (value <= next.minValue) {
					this.minValue = value;
				} else {
					this.minValue = next.minValue;
				}
			} else {
				this.minValue = value;
			}
		}
	}

	public void push(int x) {
		Node c = new Node(x, firstNode);
		this.firstNode = c;
	}

	public void pop() {
		this.firstNode = firstNode.next;
	}

	public int top() {
		return this.firstNode.value;
	}

	public int getMin() {
		return this.firstNode.minValue;
	}
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */