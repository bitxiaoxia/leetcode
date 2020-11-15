package com.bitxiaoxia.leetcode.daily20200513;

/**
 * @author JasonZhang
 * @date 2020/5/13 20:30
 */
public class SolutionRegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		if (s.length() == 0 || p.length() == 0) {
			return false;
		}

		boolean asterFlag = false;
		int pPos = 0, sPos = 0;
		for (; sPos < s.length() && pPos < p.length(); sPos++) {
			char currPChar = p.charAt(pPos);
			if (!asterFlag && pPos < p.length() - 1) {
				char nextPChar = p.charAt(pPos + 1);
				if (nextPChar == '*') {
					asterFlag = true;
				}
			}
			if (currPChar == s.charAt(sPos) || currPChar == '.') {
				//标准匹配，不处理
				if (!asterFlag) {
					pPos++;
				}
			} else if (asterFlag) {
				//*号匹配
				sPos--;
				pPos += 2;
				asterFlag = false;
			} else {
				return false;
			}
		}
		if (sPos == s.length()) {
			if (pPos == p.length()) {
				return true;
			} else if (asterFlag && pPos == p.length() - 2) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		SolutionRegularExpressionMatching solution = new SolutionRegularExpressionMatching();
		System.out.println(solution.isMatch("aab", "c*a*b"));
		System.out.println(solution.isMatch("ab", ".*b"));
	}

	public boolean isMatchWithoutAsterisk(String s, String p) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == p.charAt(i) || p.charAt(i) == '.') {
				//正常
			} else {
				return false;
			}
		}
		return true;
	}
}
