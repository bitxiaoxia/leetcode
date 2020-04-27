package com.bitxiaoxia.leetcode.daily20200427;

/**
 * @author JasonZhang
 * @date 2020/4/27 17:27
 */
public class SolutionSearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return searchBetweenBound(nums, 0, nums.length - 1, target);
    }

    private int searchBetweenBound(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
        int mid = left + (right - left) / 2;
        if (nums[left] <= target && target <= nums[right]) {
            //不存在拐点
			//递归调用本方法也可以
//            int lIndex = searchBetweenBound(nums, left, mid, target);
//			if (lIndex!=-1){
//				return lIndex;
//			}
//            int rIndex = searchBetweenBound(nums, mid + 1,right, target);
//            if (rIndex!=-1){
//            	return rIndex;
//			}
            //使用二分查找
            while (left <= right) {
				if (left == right) {
					if (nums[left] == target) {
						return left;
					} else {
						return -1;
					}
				}
                mid = left + (right - left) / 2;
                if (target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        } else if (nums[left] > nums[right]) {
            //存在拐点，可以和上面的判断条件合并
            int lIndex = searchBetweenBound(nums, left, mid, target);
            if (lIndex != -1) {
                return lIndex;
            }
            int rIndex = searchBetweenBound(nums, mid + 1, right, target);
            if (rIndex != -1) {
                return rIndex;
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static void main(String[] args) {
        SolutionSearchInRotatedSortedArray solution = new SolutionSearchInRotatedSortedArray();
        int[] nums = {7, 8, 1, 2, 3, 4, 5, 6};
        System.out.println(solution.search(nums, 1));
    }
}
