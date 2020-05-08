package com.bitxiaoxia.leetcode.daily20200502;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author JasonZhang
 * @date 2020/5/2 15:33
 */
public class SolutionLongestSubString {
	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		int lastRepeatPos = 0;
		Map<Character, Integer> charMap = new HashMap<>();
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (charMap.containsKey(c)) {
				lastRepeatPos = Math.max(lastRepeatPos,charMap.get(c)+1);
			}
			max = Math.max(max, i+1-lastRepeatPos);
			charMap.put(c, i);
		}

		return max;
	}

	private int errorOne(String s) {
		int max = 0;
		Set<Character> charSet = new HashSet<>();
		for (char c : s.toCharArray()) {
			if (charSet.contains(c)) {
				max = Math.max(max, charSet.size());
				charSet.clear();
				charSet.add(c);
			} else {
				charSet.add(c);
			}
		}
		return Math.max(max, charSet.size());
	}
}
