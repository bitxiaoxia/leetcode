package com.bitxiaoxia.leetcode.daily20200429;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonZhang
 * @date 2020/4/29 17:25
 */
public class SolutionFindInMountainArray {
	public int findInMountainArray(int target, MountainArray mountainArr) {
		return 0;
	}

	interface MountainArray {
		public int get(int index);

		public int length();
	}

	public static void main(String[] args) {
		SolutionFindInMountainArray solution = new SolutionFindInMountainArray();
		int[] testArr = {3,5,3,2,0};
		List<Integer> list = new ArrayList<>();
		for (int i : testArr) {
			list.add(i);
		}
		System.out.println(solution.findInMountainArray(0, list));
	}

	//这个做测试用
	public int findInMountainArray(int target, List<Integer> mountainArr) {
		int length = mountainArr.size();
		return mountainSearch(mountainArr, 0, length - 1, target);
	}

	private int mountainSearch(List<Integer> mountainArr, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
		if (left == right) {
			if (mountainArr.get(left) == target) {
				return left;
			} else {
				return -1;
			}
		}

		int mid = left + (right - left) / 2;
		int midValue = mountainArr.get(mid);
		int midDecValue = mountainArr.get(mid - 1);
		if (midDecValue < midValue) {//左半边是升序数组
			int leftPos = ascSearch(mountainArr, left, mid - 2, target);
			if (leftPos != -1) {
				return leftPos;
			}
			if (midDecValue == target) {
				return mid - 1;
			}
			if (midValue == target) {
				return mid;
			}
			int rightPos = mountainSearch(mountainArr, mid + 1, right, target);
			if (rightPos != -1) {
				return rightPos;
			}
		} else {//左半边是山峰数组
			int leftPos = mountainSearch(mountainArr, left, mid - 2, target);
			if (leftPos != -1) {
				return leftPos;
			}
			if (midDecValue == target) {
				return mid - 1;
			}
			if (midValue == target) {
				return mid;
			}
			int rightPos = descSearch(mountainArr, mid+1, right, target);
			if (rightPos != -1) {
				return rightPos;
			}
		}
		return -1;
	}

	private int descSearch(List<Integer> mountainArr, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
		int max = mountainArr.get(left);
		if (target == max) {
			return left;
		}
		int min = mountainArr.get(right);
		if (target == min) {
			return right;
		}
		if (target < max && target > min) {
			int mid = left + (right - left) / 2;
			int midValue = mountainArr.get(mid);
			if (midValue == target) {
				return mid;
			} else if (midValue < target) {
				return descSearch(mountainArr, left + 1, mid - 1, target);
			} else {
				return descSearch(mountainArr, mid + 1, right - 1, target);
			}
		} else {
			return -1;
		}
	}

	private int ascSearch(List<Integer> mountainArr, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
		int max = mountainArr.get(right);
		if (target == max) {
			return right;
		}
		int min = mountainArr.get(left);
		if (target == min) {
			return left;
		}
		if (target < max && target > min) {
			int mid = left + (right - left) / 2;
			int midValue = mountainArr.get(mid);
			if (midValue == target) {
				return mid;
			} else if (midValue > target) {
				return ascSearch(mountainArr, left + 1, mid - 1, target);
			} else {
				return ascSearch(mountainArr, mid + 1, right - 1, target);
			}
		} else {
			return -1;
		}
	}
}
