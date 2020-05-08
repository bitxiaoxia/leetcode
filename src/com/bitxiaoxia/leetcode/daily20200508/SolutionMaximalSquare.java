package com.bitxiaoxia.leetcode.daily20200508;

import com.bitxiaoxia.leetcode.daily20200503.SolutionMaximumSubarray;

/**
 * @author JasonZhang
 * @date 2020/5/8 17:12
 */
public class SolutionMaximalSquare {
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int line = matrix.length;
		int column = matrix[0].length;


		int[][] dpSideLength = getDpMatrix(matrix, line, column);
		int maxSideLength = 0;
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				if (dpSideLength[i + 1][j + 1] > maxSideLength) {
					maxSideLength = dpSideLength[i + 1][j + 1];
				}
			}
		}

		return maxSideLength * maxSideLength;
	}

	public int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int line = matrix.length;
		int column = matrix[0].length;
		int[][] dpSideLength = getDpMatrix(matrix, line, column);
		int maxSideLength = 0;
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				if (dpSideLength[i + 1][j + 1] > maxSideLength) {
					maxSideLength = dpSideLength[i + 1][j + 1];
				}
			}
		}
		int maxOffset = 0;
		for (int i = 0; i < line; i++) {
			int offset = -1;
			for (int j = 0; j < column; j++) {
				if (dpSideLength[i + 1][j + 1] == maxSideLength) {
					offset++;
				}
			}
			if (offset > maxOffset) {
				maxOffset = offset;
			}
		}

		for (int j = 0; j < column; j++) {
			int offset = -1;
			for (int i = 0; i < line; i++) {
				if (dpSideLength[i + 1][j + 1] == maxSideLength) {
					offset++;
				}
			}
			if (offset > maxOffset) {
				maxOffset = offset;
			}
		}

		return maxSideLength * (maxSideLength + maxOffset);
	}

	public static void main(String[] args) {
		char[][] test = {
				{'1', '1', '1', '1', '1', '1', '1', '1'},
				{'1', '1', '1', '1', '1', '1', '1', '0'},
				{'1', '1', '1', '1', '1', '1', '1', '0'},
				{'1', '1', '1', '1', '1', '0', '0', '0'},
				{'0', '1', '1', '1', '1', '0', '0', '0'}
		};
		SolutionMaximalSquare solution = new SolutionMaximalSquare();
		solution.maximalRectangle(test);
	}

	private int[][] getDpMatrix(char[][] matrix, int line, int column) {
		int[][] dpSideLength = new int[line + 1][column + 1];
		for (int i = 0; i < line; i++) {
			dpSideLength[i][0] = 0;
		}
		for (int i = 0; i < column; i++) {
			dpSideLength[0][i] = 0;
		}

		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				if (matrix[i][j] == '0') {
					dpSideLength[i + 1][j + 1] = 0;
				} else {
					dpSideLength[i + 1][j + 1] = Math.min(dpSideLength[i][j + 1] + 1, dpSideLength[i + 1][j] + 1);
					dpSideLength[i + 1][j + 1] = Math.min(dpSideLength[i + 1][j + 1], dpSideLength[i][j] + 1);
				}
				System.out.print(dpSideLength[i + 1][j + 1]+" ");
			}
			System.out.println();
		}
		return dpSideLength;
	}
}
