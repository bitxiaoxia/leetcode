package com.bitxiaoxia.leetcode.daily20200428;

public class SolutionSingleNumsInArray {
    public int[] singleNumbers(int[] nums) {
        //用于将所有的数异或起来
        int k = 0;

        for(int num: nums) {
            k ^= num;
        }

        //获得k中最低位的1
        int mask = 1;

        while((k & mask) == 0) {
            mask <<= 1;
        }

        int a = 0;
        int b = 0;

        for(int num: nums) {
            if((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }

}
