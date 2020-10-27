package com.bitxiaoxia.leetcode.contest;

import com.bitxiaoxia.Main;
import com.bitxiaoxia.leetcode.utils.PrintUtils;

import java.util.*;

/**
 * Created by Bitxiaoxia on 2020/10/25.
 */
public class Contest212 {
	public static void main(String[] args) {
//		PrintUtils.changeToArr("[[7,3,6],[1,4,5],[9,8,2]]");
		int[][] arr = {{7,7,7},{7,7,7},{7,7,7}};
		Contest212 c = new Contest212();
//		minimumEffortPath(arr);
		c.matrixRankTransform(arr);
	}

	public int[] smallerNumbersThanCurrent(int[] nums) {
		int n = nums.length;
		int[] result = Arrays.copyOf(nums,n);

		//排序+hashMap
		Arrays.sort(result);
		int current = result[0] - 1;
		HashMap<Integer,Integer> map= new HashMap<>();
		for(int i=0;i<n;i++){
			if (result[i] != current){
				map.put(result[i],i);
				current = result[i];
			}
		}

		for(int i=0;i<n;i++){
			result[i] = map.get(nums[i]);
		}
		return result;
	}

	public int[][] matrixRankTransform(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		//先排序
		PriorityQueue<EnumClass> priorityQueue = new PriorityQueue<>((e1, e2) -> (e1.value - e2.value));
		for(int i=0;i<m;i++){
			for(int j = 0;j<n;j++){
				priorityQueue.add(new EnumClass(matrix[i][j],i,j));
			}
		}
		//然后出队。
		int[][] result = new int[m][n];
		while(priorityQueue.size()!=0){
			//出队
			EnumClass e = priorityQueue.poll();
			int linRank = 0;
			int line = e.line;
			for(int i=0;i<m;i++){
				if (result[i][e.column] !=0){
					if (result[i][e.column] > linRank){
						linRank = result[i][e.column];
						line = i;
					}
				}
			}
			if (matrix[e.line][e.column] != matrix[line][e.column]){
				linRank ++;
			}

			int columnRank = 0;
			int column = e.column;
			for(int i=0;i<n;i++){
				if (result[e.line][i] !=0){
					if (result[e.line][i] > columnRank){
						columnRank = result[e.line][i];
						column = i;
					}
				}
			}
			if (matrix[e.line][e.column] != matrix[e.line][column]){
				columnRank ++;
			}
			int rank = Math.max(linRank,columnRank);
			if (rank ==0){
				rank++;
			}
			result[e.line][e.column] = rank;
		}
//		for(int i=0;i<m;i++){
//			for(int j = 0;j<n;j++){
//				System.out.print(result[i][j] + " ");
//			}
//			System.out.println();
//		}
		return result;
	}

	class EnumClass {
		int value;
		int column;
		int line;

		EnumClass(int v, int i, int j) {
			value = v;
			line = i;
			column = j;
		}
	}

	public char slowestKey(int[] releaseTimes, String keysPressed) {
		char maxChar = keysPressed.charAt(0);
		int maxTime = releaseTimes[0];
		for (int i = 1; i < keysPressed.length(); i++) {
			int time = releaseTimes[i] - releaseTimes[i - 1];
			if (time > maxTime) {
				maxChar = keysPressed.charAt(i);
				maxTime = time;
			} else if (time == maxTime) {
				if (keysPressed.charAt(i) - maxChar > 0) {
					maxChar = keysPressed.charAt(i);
				}
			}
		}
		return maxChar;
	}

	public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
		//先暴力写一次
		List<Boolean> result = new ArrayList<>();
		for (int i = 0; i < l.length; i++) {
			result.add(checkOne(nums, l[i], r[i]));
		}
		return result;
	}

	private boolean checkOne(int[] nums, int start, int end) {
		int n = end - start + 1;
		if (n <= 2) {
			return true;
		}
		int[] test = new int[n];
		for (int i = start; i <= end; i++) {
			test[i - start] = nums[i];
		}
		Arrays.sort(test);
		int diff = test[1] - test[0];
		//判断是否等差。
		for (int i = 2; i < n; i++) {
			if (test[i] - test[i - 1] != diff) {
				return false;
			}
		}
		return true;
	}

	public static int minimumEffortPath(int[][] heights) {
		int m = heights.length;
		int n = heights[0].length;
		int[][] dp = new int[m][n];
		dp[0][0] = 0;
		//先横向+纵向扫描 dp[i][j] = Math.min(heights[i][j] - heights[i-1][j]+dp[i-1][j] , heights[i][j] - heights[i][j-1]+dp[i][j-1] );
		//在纵向逆序 + 横向逆序扫描dp[i][j] = dp[i][j] 或者 Math.min(heights[i][j] - heights[i+1][j]+dp[i+1][j] , heights[i][j+1] - heights[i][j+1]+dp[i][j-1]);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = 0;
				} else if (i == 0) {
					int leftDiff = Math.abs(heights[i][j] - heights[i][j - 1]);
					dp[i][j] = Math.max(leftDiff, dp[i][j - 1]);
				} else if (j == 0) {
					int upDiff = Math.abs(heights[i - 1][j] - heights[i][j]);
					dp[i][j] = Math.max(upDiff, dp[i - 1][j]);
				} else {
					int leftDiff = Math.abs(heights[i][j] - heights[i][j - 1]);
					int leftDp = Math.max(leftDiff, dp[i][j - 1]);
					int upDiff = Math.abs(heights[i - 1][j] - heights[i][j]);
					int upDp = Math.max(upDiff, dp[i - 1][j]);
					dp[i][j] = Math.min(leftDp, upDp);
				}
			}
		}
		boolean end = true;
		do {
			System.out.println("=====================");
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(dp[i][j] + " ");
				}
				System.out.println();
			}
			end = true;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					int leftDp = Integer.MAX_VALUE;
					int rightDp = Integer.MAX_VALUE;
					int upDp = Integer.MAX_VALUE;
					int downDp = Integer.MAX_VALUE;
					if (i > 0) {
						int upDiff = Math.abs(heights[i - 1][j] - heights[i][j]);
						upDp = Math.max(upDiff, dp[i - 1][j]);
					}
					if (i < m - 1) {
						int downDiff = Math.abs(heights[i + 1][j] - heights[i][j]);
						downDp = Math.max(downDiff, dp[i + 1][j]);
					}
					if (j > 0) {
						int leftDiff = Math.abs(heights[i][j] - heights[i][j - 1]);
						leftDp = Math.max(leftDiff, dp[i][j - 1]);
					}
					if (j < n - 1) {
						int rightDiff = Math.abs(heights[i][j] - heights[i][j + 1]);
						rightDp = Math.max(rightDiff, dp[i][j + 1]);
					}
					int min = Math.min(Math.min(leftDp, upDp), Math.min(rightDp, downDp));
					if (dp[i][j] > min) {
						dp[i][j] = min;
						end = false;
					}
				}
			}
		} while (!end);

		return dp[m - 1][n - 1];
	}
}
