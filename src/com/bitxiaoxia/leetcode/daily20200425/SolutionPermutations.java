package com.bitxiaoxia.leetcode.daily20200425;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SolutionPermutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        int length = nums.length;
        if (length <= 0) {
            return resList;
        }

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] statusArr = new boolean[length];
        dfs(nums, path, 0, statusArr, resList);
        return resList;
    }

    private void dfs(int[] nums, Deque<Integer> path, int depth, boolean[] statusArr, List<List<Integer>> resList) {
        if (depth == nums.length) {
            resList.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!statusArr[i]) {
                statusArr[i] = true;
                path.addLast(nums[i]);
                dfs(nums, path, depth + 1, statusArr, resList);
                path.removeLast();
                statusArr[i] = false;
            }
        }
    }


    /**
     * 把sourceList的x和y位调换
     *
     * @param sourceList
     * @param x
     * @param y
     * @return
     */
    private List<Integer> getList(List<Integer> sourceList, int x, int y) {
        List<Integer> resList = new ArrayList<>();
        resList.addAll(sourceList);
        resList.set(x, sourceList.get(y));
        resList.set(y, sourceList.get(x));
        return resList;
    }

    public static void main(String[] args) {
        SolutionPermutations solution = new SolutionPermutations();
        int[] testArray = {1, 2, 3};
        List<List<Integer>> resList = solution.permute(testArray);
        for (List<Integer> tmp : resList) {
            for (Integer t : tmp) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }
}
