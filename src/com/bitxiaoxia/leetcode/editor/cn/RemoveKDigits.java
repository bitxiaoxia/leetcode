package com.bitxiaoxia.leetcode.editor.cn;

//给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。 
//
// 注意: 
//
// 
// num 的长度小于 10002 且 ≥ k。 
// num 不会包含任何前导零。 
// 
//
// 示例 1 : 
//
// 
//输入: num = "1432219", k = 3
//输出: "1219"
//解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
// 
//
// 示例 2 : 
//
// 
//输入: num = "10200", k = 1
//输出: "200"
//解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
// 
//
// 示例 3 : 
//
// 
//输入: num = "10", k = 2
//输出: "0"
//解释: 从原数字移除所有的数字，剩余为空就是0。
// 
// Related Topics 栈 贪心算法

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {
	public static void main(String[] args) {
		Solution solution = new RemoveKDigits().new Solution();
		System.out.println(solution.removeKdigits("100010",2));
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		public String removeKdigits(String num, int k) {
			//移除一位的话，只要当前位置比后一位大，就移除这一位。
			//如果按照升序排列，则移除最后一位。
			//如果移除n位，则每次移除一位即可。
//        for(int i=0;i<k;i++){
//            num = removeOneDigits(num);
//        }
//        return num;
			return removeByMinStack(num, k);
		}

		public String removeByMinStack(String num, int k) {
			Deque<Integer> deque = new ArrayDeque<>();
			for (char c : num.toCharArray()) {
				int i = c - '0';
				while (deque.size() > 0 && k > 0 && deque.peekLast() > i) {
					deque.pollLast();
					k--;
				}
				deque.addLast(i);
			}
			//经过前面的计算，如果k!=0,则保证了队列是升序。否则k=0
			while (k > 0 && deque.size() > 0) {
				deque.pollLast();
				k--;
				System.out.println(k);
			}

			StringBuilder builder = new StringBuilder();
			while (deque.size() > 0) {
				int i = deque.pollFirst();
				if (builder.length() != 0 || i != 0) {
					builder.append(i);
				}
			}
			return builder.length() == 0 ? "0" : builder.toString();
		}

		public String removeOneDigits(String num) {
			int n = num.length();
			if (n == 1) {
				return "0";
			}
			for (int i = 1; i < n; i++) {
				if (num.charAt(i - 1) - num.charAt(i) > 0) {
					if (i == 1) {
						num = num.substring(1, n);
						while (num.length() > 1 && num.charAt(0) == '0') {
							num = num.substring(1, num.length());
						}
					} else {
						num = num.substring(0, i - 1) + num.substring(i, n);
					}
					break;
				}
			}
			if (num.length() == n) {
				num = num.substring(0, n - 1);
			}
			return num;
		}
	}
//leetcode submit region end(Prohibit modification and deletion)

}