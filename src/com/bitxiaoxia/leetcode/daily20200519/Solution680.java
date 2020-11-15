package com.bitxiaoxia.leetcode.daily20200519;

/**
 * @author JasonZhang
 * @date 2020/5/19 12:07
 */
public class Solution680 {
	public boolean validPalindrome(String s) {
		return verifyStr(s,false);
	}

	private boolean verifyStr(String s, boolean deletedOne) {
		if (s.length() <= 1) {
			return true;
		} else if (s.charAt(0) == s.charAt(s.length() - 1)) {
			return verifyStr(s.substring(1, s.length() - 1), deletedOne);
		} else {
			if (deletedOne) {
				return false;
			} else {
				deletedOne = true;
				boolean left = verifyStr(s.substring(1, s.length()), deletedOne);
				if (!left) {
					return verifyStr(s.substring(0, s.length() - 1), deletedOne);
				} else {
					return true;
				}
			}
		}
	}

	public static void main(String[] args) {
		Solution680 solution680 = new Solution680();
		String s = "cupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupucu";
		System.out.println(solution680.validPalindrome(s));
	}
}
