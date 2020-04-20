package com.bitxiaoxia.leetcode.daily20200420;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.Arrays;

public class SolutionNumberOfIslands {
    public static int numIslands(char[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    //TODO 广搜，把flagGrid对应的位置全部置为0
                    setBit(grid, i, j);
                    printGrid(grid);
                } else {
                    continue;
                }
            }
        }
        return cnt;
    }

    private static void setBit(char[][] originalMap, int i, int j) {
        if (i < 0 || i >= originalMap.length) {
            return;
        }
        if (j < 0 || j >= originalMap[0].length) {
            return;
        }
        if (originalMap[i][j] == '1') {
            originalMap[i][j] = '0';
            setBit(originalMap, i - 1, j);
            setBit(originalMap, i, j - 1);
            setBit(originalMap, i, j + 1);
            setBit(originalMap, i + 1, j);
        }
    }


    private static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String input = "10111\n" +
                "10101\n" +
                "11101";

        String[] line = input.split("\n");
        char[][] grid = new char[line.length][];
        for (int i = 0; i < line.length; i++) {
            grid[i] = line[i].toCharArray();
        }
        printGrid(grid);
        System.out.println(numIslands(grid));
    }
}
