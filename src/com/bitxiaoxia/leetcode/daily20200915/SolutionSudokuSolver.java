package com.bitxiaoxia.leetcode.daily20200915;

/**
 * Created by Bitxiaoxia on 2020/9/15.
 */
public class SolutionSudokuSolver {
	public static void main(String[] args) {
		SolutionSudokuSolver solver = new SolutionSudokuSolver();
//		char[][] tempArr = {
//				{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//				{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//				{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//
//				{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//				{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//				{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//
//				{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//				{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//				{'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

		char[][] tempArr = {{'.','.','9','7','4','8','.','.','.'},
				{'7','.','.','.','.','.','.','.','.'},
				{'.','2','.','1','.','9','.','.','.'},
				{'.','.','7','.','.','.','2','4','.'},
				{'.','6','4','.','1','.','5','9','.'},
				{'.','9','8','.','.','.','3','.','.'},
				{'.','.','.','8','.','3','.','2','.'},
				{'.','.','.','.','.','.','.','.','6'},
				{'.','.','.','2','7','5','9','.','.'}};

		solver.solveSudoku(tempArr);
	}

	public void solveSudoku(char[][] board) {
		//通过位运算，填入能够写哪个数字。
		//int的32位。从高位到低位。前八位表示有多少可能性，之后十位为为可能值，再十位是已设置过的值，再四位是当前值。
		//当前值是x & 0x0000 000F; 已经设置过的是x & 0x0000 3FF0; 可以设置的为x & 0x00FF C000
		int[] line = new int[9];
		int[] column = new int[9];
		int[][] block = new int[3][3];
		int waitCount = 0;
		for (int i = 0; i < 9; i++) {
			//第一次初始化设置数组。
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					waitCount++;
				} else {
					int temp = (1 << (board[i][j] - '1'));
					line[i] |= temp;
					column[j] |= temp;
					block[i / 3][j / 3] |= temp;
				}
			}
		}
		int currWaitCount = waitCount;
		while (waitCount > 0) {
			for (int i = 0; i < 9; i++) {
				//第一次初始化设置数组。
				for (int j = 0; j < 9; j++) {
					if (board[i][j] == '.') {
						int appeardNum = line[i] | column[j] | block[i / 3][j / 3];

						int count = 9;
						for (int k = 0; k < 9; k++) {
							if ((appeardNum & (1 << k)) != 0) {
								count--;
							}
						}
						if (count == 1) {
							currWaitCount--;
							int real = 0;
							for (int k = 0; k < 9; k++) {
								if ((appeardNum & (1 << k)) == 0) {
									real = k;
								}
							}
							board[i][j] = (char) ('1' + real);
							int temp = 1<<real;
							line[i] |= temp;
							column[j] |= temp;
							block[i / 3][j / 3] |= temp;
						}
					}
				}
			}
			if(currWaitCount == waitCount){
				//表示没有找到新的值，需要进行尝试。
				break;
			}else{
				waitCount = currWaitCount;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				System.out.println();
			}
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0) {
					System.out.print("| ");
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 根据行列，得到当前可能值，先暴力来吧
	 */
	private int getPossibleNum(char[][] board, int line, int column) {
		int a = 0x000001FF;
		int b = 0;
		for (int i = 0; i < 9; i++) {
			if (board[line][i] != '.') {
				int temp = (board[line][i] - '1');
				b = b | (1 << temp);
			}
			if (board[i][column] != '.') {
				int temp = (board[i][column] - '1');
				b = b | (1 << temp);
			}
		}

		int lineStart = line - line % 3;
		int columnStart = column - column % 3;

		for (int i = lineStart; i < lineStart + 3; i++) {
			for (int j = columnStart; j < columnStart + 3; j++) {
				if (board[i][j] != '.') {
					int temp = (board[i][j] - '1');
					b = b | (1 << temp);
				}
			}
		}
		int count = 9;
		for (int i = 0; i < 9; i++) {
			if ((b & (1 << i)) != 0) {
				count--;
			}
		}
		if (count == 1) {
			for (int i = 0; i < 9; i++) {
				if ((b & (1 << i)) == 0) {
					return i + 1;
				}
			}
		}
		return (a ^ b) | (count << 24);
	}
}
