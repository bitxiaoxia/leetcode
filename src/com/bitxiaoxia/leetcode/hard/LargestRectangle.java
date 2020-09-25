package com.bitxiaoxia.leetcode.hard;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * Created by Bitxiaoxia on 2020/9/23.
 */
public class LargestRectangle {
	public static void main(String[] args) {
//		LargestRectangle s = new LargestRectangle();
//		int[] heights = {3, 6, 5, 7, 4, 8, 1, 0};
////		int[] heights = {1,2,3,4,5,};
//		List<Integer> list = new ArrayList<>();
//		list.remove(0);
//		System.out.println(s.largestRectangleArea(heights));
//		System.out.println(s.largestRectangleArea3(heights));

		Comparator<Integer> ic = (x,y) -> (y-x) ;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x,y)->y-x);

		maxHeap.add(1);
		maxHeap.add(2);
		maxHeap.add(4);
		maxHeap.add(3);

		while(maxHeap.size() >0){
			System.out.println(maxHeap.poll());
		}
	}
	public int largestRectangleArea3(int[] heights) {
		// min[i][j] 表示第i到第j列的最小值。（i<=j）
		// 初始化,min[i][i] = height[i];
		// 之后,遍历一次
		int n= heights.length;
		if(n==0){
			return  0;
		}
		int max = 0;
		int[] min = new int[n];
		for(int i=0;i<n;i++){
			min[i] = heights[i];
			max = Math.max(max,heights[i]);
			for(int j=i+1;j<n;j++){
				min[j] = Math.min(heights[j], min[j-1]);
				max = Math.max(max,(j-i+1) * min[j]);
			}
		}
		return max;
	}

	public int largestRectangleArea(int[] heights) {
		//单调栈，比栈顶高的入栈。
		int n = heights.length;
		int[][] bound = new int[n][2];
		for(int i=0;i<n;i++){
			bound[i][0] = -1;
			bound[i][1] = n;
		}
		Deque<Integer> deque = new ArrayDeque<>();//存储位置。
		deque.push(-1);
		//左边界
		for (int i = 0; i < n; i++) {
			while (deque.peek()!=-1 && heights[deque.peek()] >= heights[i]) {
				int pos = deque.pop();
				bound[pos][1] = i;
			}
			bound[i][0] = deque.peek();
			deque.push(i);
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			int sum = heights[i] * (bound[i][1] - bound[i][0] - 1);
//			System.out.println(String.format("第%s根柱子\t高度:%s\t左边界:%s\t右边界:%s\t面积:%s",i,heights[i],bound[i][0],bound[i][1],sum));
			if (sum > max) {
				max = sum;
			}
		}

		return max;
	}


	public int largestRectangleArea2(int[] heights) {
		//从低到高，假设两边的边界高度都是0。对每个柱形图找到自己两边比他低的柱子
		//对应例题，那么边界数组 bound[][] = {{-1,1},{-1,6},{1,4},{2,4},{1,6},{4,6}}
		//则对应的面积只需要乘上当前柱子的高度即可。

		//下面是对应边界的情况,记录下当前
		//对每一个数字，如果h[n] > h[n-1], 那么bound[n][0] = n-1;
		//如果h[n] = h[n-1],那么bound[n][0] = bound[n-1][0];
		//如果h[n] < h[n-1],那么bound[n-1][1] = n;bound[n][0] = 完成出栈后的栈顶。
		//采用单调栈，只要当前位置比栈顶高，就入栈

		//对于bound[n][1] 只需要反向遍历一次即可。
		int n = heights.length;
		if (n == 0) {
			return 0;
		}

		Deque<HeightInfo> minStack = new ArrayDeque<>();
		int[][] bound = new int[n][2];
		bound[0][0] = -1;
		bound[n - 1][1] = n;

		minStack.push(new HeightInfo(-1, -1));
		minStack.push(new HeightInfo(heights[0], 0));
		//先找左边界
		for (int i = 1; i < heights.length; i++) {
			while (minStack.peek().val >= heights[i]) {
				minStack.pop();
			}
			bound[i][0] = minStack.peek().pos;
			minStack.push(new HeightInfo(heights[i], i));
		}

		minStack.clear();
		minStack.push(new HeightInfo(-1, n));
		minStack.push(new HeightInfo(heights[n - 1], n - 1));
		//再找右边界
		for (int i = n - 2; i >= 0; i--) {
			while (minStack.peek().val >= heights[i]) {
				minStack.pop();
			}
			bound[i][1] = minStack.peek().pos;
			minStack.push(new HeightInfo(heights[i], i));
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			int sum = heights[i] * (bound[i][1] - bound[i][0] - 1);
			if (sum > max) {
				max = sum;
			}
		}

		return max;
	}

	class HeightInfo {
		int val;
		int pos;

		HeightInfo(int val, int pos) {
			this.val = val;
			this.pos = pos;
		}
	}
}
